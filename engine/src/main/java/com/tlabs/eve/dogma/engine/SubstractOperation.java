package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class SubstractOperation extends AbstractFittingOperation {
    public SubstractOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result addResult1 = evaluate(expr.getArg1(), model);
        Expression.Result addResult2 = evaluate(expr.getArg2(), model);
        return new Expression.Result(addResult1.getValue() - addResult2.getValue());
    }
}
