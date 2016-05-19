package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Expression;
import com.tlabs.eve.dogma.model.Operand;

abstract class ItemModOperation extends AbstractFittingOperation {

    public ItemModOperation(FittingEngine engine) {
        super(engine);
    }

    protected static int getModifiedAttributeForItemMod(final Expression expr) {
        if (null == expr) {
            throw new IllegalArgumentException("Attribute not found");
        }

        Integer modifiedAttributeId = getModifiedAttributeFromArg(expr.getArg1());
        if (null == modifiedAttributeId) {
            modifiedAttributeId = getModifiedAttributeFromArg(expr.getArg2());
        }
        if (null == modifiedAttributeId) {
            return getModifiedAttributeForItemMod(expr.getParent());
        }
        return modifiedAttributeId;
    }

    protected static FittingModel getModifiedModelForItemMod(Expression expr, FittingModel src) {
        int domain;
        FittingModel modifiedModel = null;
        do {
            if (expr.getOperand().getOperandID() == Operand.OperatorTarget) {
                Expression domainExpr = expr.getArg2().getArg1();
                domain = FittingEngine.Domain.from(domainExpr.getValue());
                modifiedModel = getAttributeModel(domain, src);
                if (modifiedModel != null) {
                    break;
                }
            }
            expr = expr.getParent();
        } while (expr != null);

        return modifiedModel;
    }

    protected static FittingModel getAttributeModel(int domain, FittingModel model) {
        switch (domain) {
            case FittingEngine.Domain.Self:
                return model;
            case FittingEngine.Domain.Ship:
                if (model instanceof FittingModel.Ship) {
                    return model;
                }
                return ((FittingModel.Module)model).getShip();
            default:
                // TODO: handle other domains necessary?
                return model;
        }
    }

    private static Integer getModifiedAttributeFromArg(final Expression arg) {
        if (null == arg) {
            return null;
        }
        if (!Integer.valueOf(arg.getOperand().getOperandID()).equals(Operand.ItemAttribute)) {
            return null;
        }
        final Attribute attribute = arg.getArg2().getAttribute();
        return (null == attribute) ? null : (int)attribute.getAttributeID();
    }

}
