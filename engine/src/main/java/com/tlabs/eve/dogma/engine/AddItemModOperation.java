package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;
import com.tlabs.eve.dogma.model.Operator;

final class AddItemModOperation extends ItemModOperation {
    public AddItemModOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        //case Dogma.Operand.AddItemMod: // Add Item Modifier - AIM (6)
        Expression.Result aimResult1 = evaluate(expr.getArg1(), model);
        Expression.Result aimResult2 = evaluate(expr.getArg2(), model);
        double aimValue1 = aimResult1.getValue();
        double aimValue2 = aimResult2.getValue();

        // Get the affected attribute
        int attributeId = getModifiedAttributeForItemMod(expr.getArg1());
        FittingModel modifiedModel = getModifiedModelForItemMod(expr.getArg1(), model);
        Operator operator = aimResult1.getOperator();
        operator.setAttributeId(attributeId);
        operator.setRemoving(false);

        double aimNewValue = operator.apply(model, modifiedModel, aimValue1, aimValue2);
        //updateAffectedValue(arg1, src, aimNewValue, false);

        return new Expression.Result(aimNewValue);
    }

}
