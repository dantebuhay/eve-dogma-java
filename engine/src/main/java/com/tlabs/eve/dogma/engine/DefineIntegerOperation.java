package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class DefineIntegerOperation extends AbstractFittingOperation {

    public DefineIntegerOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        try {
            return new Expression.Result(Integer.parseInt(expr.getValue()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(expr.getValue() + ":" + e.getMessage());
        }
    }
}
