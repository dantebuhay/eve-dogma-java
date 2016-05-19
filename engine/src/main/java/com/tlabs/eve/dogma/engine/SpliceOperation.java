package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;


final class SpliceOperation extends AbstractFittingOperation {
    public SpliceOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result spliceResult1 = evaluate(expr.getArg1(), model);
        Expression.Result spliceResult2 = evaluate(expr.getArg2(), model);
        //LOG.warn("SpliceOperation not implemented");
        return new Expression.Result(1);
    }
}
