package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;
import com.tlabs.eve.dogma.model.Operator;

final class DefineOperatorOperation extends AbstractFittingOperation {

    public DefineOperatorOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        return new Expression.Result(new Operator(expr.getValue()));
    }
}
