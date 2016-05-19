package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class OrOperation extends AbstractFittingOperation {
    public OrOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result orResult1 = evaluate(expr.getArg1(), model);
        if (orResult1.getBooleanValue()) {
            // No need to evaluate second expression if the first returns true
            return new Expression.Result(1);
        }

        Expression.Result orResult2 = evaluate(expr.getArg2(), model);
        return new Expression.Result((orResult1.getBooleanValue() && orResult2.getBooleanValue()) ? 1 : 0);
    }
}
