package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class AndOperation extends AbstractFittingOperation {
    public AndOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result andResult1 = evaluate(expr.getArg1(), model);
        if (!andResult1.getBooleanValue() && !expr.getName().contains("SkillCheck")) { // TODO: Skill check for later?
            // No need to evaluate second expression if the first returns false
            return new Expression.Result(0);
        }

        if (expr.getName().contains("SkillCheck")) {
            // Always return true for skill check
            andResult1 = new Expression.Result(1);
        }
        Expression.Result andResult2 = evaluate(expr.getArg2(), model);
        return new Expression.Result((andResult1.getBooleanValue() && andResult2.getBooleanValue()) ? 1 : 0);
    }
}
