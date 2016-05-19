package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class ItemAttributeConditionOperation extends ItemModOperation {
    public ItemAttributeConditionOperation(FittingEngine engine) {
        super(engine);
    }
    /*

            case Dogma.Operand.ItemAttributeCondition: // Item Attribute Condition (35)
                if (arg1 != null && arg2 != null) {
                    Result locationResult = evaluate(arg1, arg1.getArgument1(), arg1.getArgument2(), src);
                    FittingModel attributeModel = getAttributeModel(locationResult.getIntValue(), src);
                    if (attributeModel != null) {
                        return evaluate(arg2, arg2.getArgument1(), arg2.getArgument2(), attributeModel);
                    }
                }
                break;

    }*/
    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result locationResult = evaluate(expr.getArg1(), model);
        FittingModel attributeModel = getAttributeModel((int)locationResult.getValue(), model);
        if (null == attributeModel) {
            throw new IllegalStateException("No attribute model");
        }
        return evaluate(expr.getArg2(), attributeModel);
    }
}
