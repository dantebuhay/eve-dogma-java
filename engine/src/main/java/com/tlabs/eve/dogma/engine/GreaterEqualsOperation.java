package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class GreaterEqualsOperation extends AbstractFittingOperation {
    public GreaterEqualsOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result gtResult1 = evaluate(expr.getArg1(), model);
        Expression.Result gtResult2 = evaluate(expr.getArg2(), model);
        return new Expression.Result((gtResult1.getValue() >= gtResult2.getValue()) ? 1 : 0);
    }
}
