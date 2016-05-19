package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class AddLocationRequiredSkillOperation extends AbstractFittingOperation {

    public AddLocationRequiredSkillOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        return new Expression.Result(1);//TODO
    }
}
