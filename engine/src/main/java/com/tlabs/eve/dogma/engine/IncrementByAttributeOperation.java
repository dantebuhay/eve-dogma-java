package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class IncrementByAttributeOperation extends AbstractFittingOperation {
    public IncrementByAttributeOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result incResult1 = evaluate(expr.getArg1(), model);
        Expression.Result incResult2 = evaluate(expr.getArg2(), model);
        double incResultValue = incResult1.getValue() + incResult2.getValue();
        return new Expression.Result(incResultValue);
    }
}
