package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

public class CheckToolTargetSkillsOperation extends AbstractFittingOperation {
    public CheckToolTargetSkillsOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        LOG.warn("CheckToolTargetSkillsOperation not implemented");
        return new Expression.Result(1);
    }
}
