package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class DefineBooleanOperation extends AbstractFittingOperation {
    public DefineBooleanOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        return new Expression.Result("1".equals(expr.getValue()) ? 1 : 0);
    }
}
