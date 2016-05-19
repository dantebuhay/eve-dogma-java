package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class DefineLocationOperation extends AbstractFittingOperation {

    public DefineLocationOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        return new Expression.Result(FittingEngine.Domain.from(expr.getValue()));
    }
}
