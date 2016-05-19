package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

public class SkillCheckOperation extends AbstractFittingOperation {
    public SkillCheckOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        LOG.warn("SkillCheckOperation not implemented");
        return new Expression.Result(1);
    }
}
