package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;
import com.tlabs.eve.dogma.model.Operator;


final class RemoveItemModOperation extends ItemModOperation {
    public RemoveItemModOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result rimResult1 = evaluate(expr.getArg1(), model);
        Expression.Result rimResult2 = evaluate(expr.getArg2(), model);
        double rimValue1 = rimResult1.getValue();
        double rimValue2 = rimResult2.getValue();

        int rimAttributeId = getModifiedAttributeForItemMod(expr.getArg1());
        FittingModel modifiedModel = getModifiedModelForItemMod(expr.getArg1(), model);
        Operator operator = rimResult1.getOperator();
        operator.setAttributeId(rimAttributeId);
        operator.setRemoving(true);

        double rimNewValue = rimResult1.getOperator().apply(model, modifiedModel, rimValue1, -rimValue2);
        //updateAffectedValue(arg1, src, rmNewValue, true);

        return new Expression.Result(rimNewValue);
    }
}
