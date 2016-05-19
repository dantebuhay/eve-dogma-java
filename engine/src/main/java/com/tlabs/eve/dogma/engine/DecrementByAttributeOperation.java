package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class DecrementByAttributeOperation extends AbstractFittingOperation {
    public DecrementByAttributeOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result decResult1 = evaluate(expr.getArg1(), model);
        Expression.Result decResult2 = evaluate(expr.getArg2(), model);
        double decResultValue = decResult1.getValue() + decResult2.getValue();
        return new Expression.Result(decResultValue);
    }
}
