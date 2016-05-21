package com.tlabs.eve.dogma.ormlite;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.LruObjectCache;
import com.j256.ormlite.support.ConnectionSource;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Effect;
import com.tlabs.eve.dogma.model.Expression;
import com.tlabs.eve.dogma.model.Item;
import com.tlabs.eve.dogma.model.Operand;
import com.tlabs.eve.dogma.model.Trait;
import com.tlabs.eve.dogma.model.Unit;

final class OrmLiteHelper {

    private final Dao<EffectEntity, Long> effectDAO;
    private final Dao<AttributeEntity, Long> attributeDAO;
    private final Dao<TraitEntity, Long> traitDAO;
    private final Dao<ExpressionEntity, Long> expressionDAO;
    private final Dao<OperandEntity, Long> operandDAO;
    private final Dao<UnitEntity, Long> unitDAO;

    private final ConnectionSource connection;

    OrmLiteHelper(final ConnectionSource connection) throws SQLException {
        this.connection = connection;

        this.effectDAO = DaoManager.createDao(connection, EffectEntity.class);
        this.effectDAO.setObjectCache(new LruObjectCache(10));

        this.attributeDAO = DaoManager.createDao(connection, AttributeEntity.class);
        this.attributeDAO.setObjectCache(new LruObjectCache(25));

        this.traitDAO = DaoManager.createDao(connection, TraitEntity.class);
        this.traitDAO.setObjectCache(new LruObjectCache(10));

        this.expressionDAO = DaoManager.createDao(connection, ExpressionEntity.class);
        this.expressionDAO.setObjectCache(new LruObjectCache(25));

        this.operandDAO = DaoManager.createDao(connection, OperandEntity.class);
        this.operandDAO.setObjectCache(new LruObjectCache(25));

        this.unitDAO = DaoManager.createDao(connection, UnitEntity.class);
        this.unitDAO.setObjectCache(new LruObjectCache(25));
    }

    public void init() throws SQLException {
        OrmLiteInit.run(this.connection);
    }

    public Item transform(final TypeEntity e) throws SQLException {
        final Item item = new Item();
        item.setItemID(e.getTypeID());
        item.setGroupID(e.getGroupID());
        item.setGroupName(e.getGroupName());
        item.setCategoryID(e.getCategoryID());
        item.setCategoryName(e.getCategoryName());
        item.setMarketGroupID(e.getMarketGroupID());
        item.setMarketGroupName(e.getMarketGroupName());
        item.setItemName(e.getTypeName());
        item.setDescription(e.getDescription());
        item.setAttributes(findAttributes(e.getTypeID()));
        item.setEffects(findEffects(e.getTypeID()));
        item.setTraits(findTraits(e.getTypeID()));
        return item;
    }

    private Effect transform(final EffectEntity e) throws SQLException {
        final Effect effect = new Effect();
        effect.setEffectID(e.getEffectID());
        effect.setEffectName(e.getEffectName());
        effect.setDisplayName(e.getDisplayName());
        effect.setDescription(e.getDescription());
        effect.setPostExpression(findExpression(e.getPostExpression()));
        effect.setPreExpression(findExpression(e.getPreExpression()));
        return effect;
    }

    private Expression transform(final ExpressionEntity e) throws SQLException {
        final Expression expression = new Expression();
        expression.setExpressionID(e.getExpressionID());
        expression.setName(e.getExpressionName());
        expression.setDescription(e.getDescription());
        expression.setValue(e.getExpressionValue());

        expression.setAttribute(findAttribute(e.getExpressionAttributeID()));
        expression.setOperand(findOperand(e.getOperandID()));
        expression.setArg1(findExpression(e.getArg1()));
        expression.setArg2(findExpression(e.getArg2()));
        return expression;
    }

    private Attribute transform(final AttributeEntity e) throws SQLException {
        final Attribute attribute = new Attribute();
        attribute.setAttributeID(e.getAttributeID());
        attribute.setAttributeName(e.getAttributeName());
        attribute.setCategoryID(e.getCategoryID());
        attribute.setUnitID(e.getUnitID());
        attribute.setDefaultValue(e.getDefaultValue());
        attribute.setDescription(e.getDescription());
        attribute.setHighIsGood(e.getHighIsGood());
        attribute.setStackable(e.getStackable());

        return attribute;
    }

    private Operand transform(final OperandEntity e) throws SQLException {
        final Operand operand = new Operand();
        operand.setDescription(e.getDescription());
        operand.setArg1CategoryID(e.getArg1CategoryID());
        operand.setArg2CategoryID(e.getArg2CategoryID());
        operand.setFormat(e.getFormat());
        operand.setOperandID(e.getOperandID());
        operand.setOperandKey(e.getOperandKey());
        operand.setResultCategoryID(e.getResultCategoryID());
        return operand;
    }

    private Trait transform(final TraitEntity e) throws SQLException {
        final Trait trait = new Trait();
        trait.setBonus(e.getBonus());
        trait.setSkillID(e.getSkillID());
        trait.setText(e.getBonusText());
        trait.setTypeID(e.getTypeID());
        trait.setUnit(findUnit(e.getUnitID()));
        return trait;
    }

    private Unit transform(final UnitEntity e) throws SQLException {
        final Unit unit = new Unit();
        unit.setId(e.getId());
        unit.setDescription(e.getDescription());
        unit.setDisplayName(e.getDisplayName());
        unit.setName(e.getName());
        return unit;
    }

    private Effect findEffect(long effectID) throws SQLException {
        final EffectEntity e = effectDAO.queryForId(effectID);
        return (null == e) ? null : transform(e);
    }

    private List<Effect> findEffects(long itemID) throws SQLException {
        List<String[]> ids =
                effectDAO.queryRaw("select effectID from dgmTypeEffects where typeID = ?", Long.toString(itemID)).getResults();

        final List<Effect> effects = new LinkedList<>();
        for (String[] r: ids) {
            effects.add(findEffect(Long.parseLong(r[0])));
        }
        return effects;
    }

    private List<Trait> findTraits(long itemID) throws SQLException {
        final List<Trait> returned = new LinkedList<>();
        final Iterator<TraitEntity> i =
                traitDAO
                .queryBuilder()
                .where()
                .eq("typeID", itemID)
                .iterator();
        while (i.hasNext()) {
            returned.add(transform(i.next()));
        }
        return returned;
    }

    private Unit findUnit(long unitID) throws SQLException {
        final UnitEntity e = unitDAO.queryForId(unitID);
        return (null == e) ? null : transform(e);
    }

    private Map<Integer, Attribute> findAttributes(long itemID) throws SQLException {
        List<String[]> ids =
                effectDAO.queryRaw("select attributeID, valueFloat, valueInt from dgmTypeAttributes where typeID = ?", Long.toString(itemID)).
                        getResults();

        final Map<Integer, Attribute> attributes = new HashMap<>();
        for (String[] r: ids) {
            final Attribute attribute = findAttribute(Long.parseLong(r[0]));
            float value = attribute.getDefaultValue();

            if (StringUtils.isNotBlank(r[1])) {
                value = Float.parseFloat(r[1]);
            }
            else if (StringUtils.isNotBlank(r[2])) {
                value = Float.parseFloat(r[2]);
            }
            attribute.setCurrentValue(value);
            attributes.put(attribute.getAttributeID(), attribute);
        }
        return attributes;
    }

    private Attribute findAttribute(final long attributeID) throws SQLException {
        final AttributeEntity e = attributeDAO.queryForId(attributeID);
        return (null == e) ? null : transform(e);
    }

    private Expression findExpression(final long expressionID) throws SQLException {
        final ExpressionEntity e = expressionDAO.queryForId(expressionID);
        return (null == e) ? null : transform(e);
    }

    private Operand findOperand(final long operandID) throws SQLException {
        final OperandEntity e = operandDAO.queryForId(operandID);
        return (null == e) ? null : transform(e);
    }
}
