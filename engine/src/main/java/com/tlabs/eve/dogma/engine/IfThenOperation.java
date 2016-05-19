package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class IfThenOperation extends AbstractFittingOperation {
    public IfThenOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result ifResult = evaluate(expr.getArg1(), model);

        if (ifResult.getBooleanValue()) {
            return evaluate(expr.getArg2(), model);
        }
        return new Expression.Result(0);
    }
}
