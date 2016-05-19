package com.tlabs.eve.dogma.ormlite;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


final class OrmLiteInit {
    private static final Logger LOG = LoggerFactory.getLogger(OrmLiteInit.class);

    private static final String VIEW_ITEMS = "CREATE VIEW IF NOT EXISTS dgmTypes AS \n" +
            "SELECT \n" +
            "invTypes.typeID, invTypes.typeName,\n" +
            "invTypes.description,\n" +
            "invTypes.mass, invTypes.volume, invTypes.capacity, invTypes.portionSize,\n" +
            "invTypes.raceID, invTypes.basePrice,\n" +
            "invTypes.published, invTypes.marketGroupID,invTypes.iconID, \n" +
            "invGroups.groupID, invGroups.groupName,\n" +
            "invGroups.categoryID, invCategories.categoryName, \n" +
            "invMetaTypes.metaGroupID, invMetaGroups.metaGroupName,\n" +
            "invMarketGroups.marketGroupName, \n" +
            "invMarketGroups.description as marketGroupDescription \n" +
            "FROM invTypes \n" +
            "LEFT JOIN invGroups ON invTypes.groupID = invGroups.groupID \n" +
            "LEFT JOIN invCategories ON invGroups.categoryID = invCategories.categoryID \n" +
            "LEFT JOIN invMetaTypes ON invTypes.typeID = invMetaTypes.typeID \n" +
            "LEFT JOIN invMetaGroups ON invMetaTypes.metaGroupID = invMetaGroups.metaGroupID \n" +
            "LEFT JOIN invMarketGroups ON invMarketGroups.marketGroupID = invTypes.marketGroupID \n" +
            "ORDER BY invTypes.typeID";

    private static final String VIEW_REQUIREMENTS = "CREATE VIEW IF NOT EXISTS dgmRequirements AS \n" +
            "SELECT\n" +
            "  Ships.typeName AS typeName,\n" +
            "  Ships.typeID AS typeID,\n" +
            "  Grouping.groupName AS typeGroupName,\n" +
            "  Grouping.groupID AS typeGroupID,\n" +
            "  Skills.typeName AS requiredTypeName,\n" +
            "  Skills.typeID as requiredTypeID,\n" +
            "  COALESCE(\n" +
            "      SkillLevel.valueInt,\n" +
            "      SkillLevel.valueFloat\n" +
            "  ) AS requiredLevel\n" +
            "FROM\n" +
            "  dgmTypeAttributes AS SkillName\n" +
            "\n" +
            "  INNER JOIN invTypes AS Ships\n" +
            "    ON Ships.typeID = SkillName.typeID\n" +
            "  INNER JOIN invGroups AS Grouping\n" +
            "    ON Grouping.groupID = Ships.groupID\n" +
            "  INNER JOIN invTypes AS Skills\n" +
            "    ON (Skills.typeID = SkillName.valueInt OR Skills.typeID = SkillName.valueFloat)\n" +
            "       AND SkillName.attributeID IN (182, 183, 184, 1285, 1289, 1290)\n" +
            "  INNER JOIN dgmTypeAttributes AS SkillLevel\n" +
            "    ON SkillLevel.typeID = SkillName.typeID\n" +
            "       AND SkillLevel.attributeID IN (277, 278, 279, 1286, 1287, 1288)\n" +
            "WHERE\n" +
            "\n" +
            "  Ships.published = 1 AND\n" +
            "  ((SkillName.attributeID = 182 AND\n" +
            "    SkillLevel.attributeID = 277) OR\n" +
            "   (SkillName.attributeID = 183 AND\n" +
            "    SkillLevel.attributeID = 278) OR\n" +
            "   (SkillName.attributeID = 184 AND\n" +
            "    SkillLevel.attributeID = 279) OR\n" +
            "   (SkillName.attributeID = 1285 AND\n" +
            "    SkillLevel.attributeID = 1286) OR\n" +
            "   (SkillName.attributeID = 1289 AND\n" +
            "    SkillLevel.attributeID = 1287) OR\n" +
            "   (SkillName.attributeID = 1290 AND\n" +
            "    SkillLevel.attributeID = 1288))\n";

    private OrmLiteInit() {}

    public static void run(final ConnectionSource connection) throws SQLException {
        initDgmTypes(connection);
        initOperands(connection);
    }

    private static void initDgmTypes(final ConnectionSource connection) throws SQLException {
        final Dao<TypeEntity, Long> itemDAO = DaoManager.createDao(connection, TypeEntity.class);
        itemDAO.executeRaw(VIEW_ITEMS);
        itemDAO.executeRaw(VIEW_REQUIREMENTS);
    }

    private static void initOperands(final ConnectionSource connection) throws SQLException {
        final Dao<OperandEntity, Long> operandDAO = DaoManager.createDao(connection, OperandEntity.class);

        try {
            if (null != operandDAO.queryForId(5l)) {
                return;
            }
        }
        catch (SQLException e) {
        }

        try {
            TableUtils.dropTable(connection, OperandEntity.class, true);
            TableUtils.createTable(connection, OperandEntity.class);
            for (OperandEntity o: loadOperandsJSON()) {
                operandDAO.create(o);
            }
        }
        catch (IOException e) {
            throw new SQLException(e);
        }
    }

    private static List<OperandEntity> loadOperandsJSON() throws IOException {
        InputStream in = OrmLiteInit.class.getResourceAsStream("/dgmOperands.json");
        if (null == in) {
            throw new IOException("/dgmOperands.json  not found");
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final Map<Long, OperandEntity> operands = mapper.readValue(in, new TypeReference<Map<String, OperandEntity>>() {});
            final List<OperandEntity> returned = new ArrayList<>(operands.size());
            returned.addAll(operands.values());
            return returned;
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ignored) {}
        }
    }

}
