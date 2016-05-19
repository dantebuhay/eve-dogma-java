package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

public class VerifyTargetGroupOperation extends AbstractFittingOperation {
    public VerifyTargetGroupOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        LOG.warn("VerifyTargetGroupOperation not implemented");
        return new Expression.Result(1);
    }
}
