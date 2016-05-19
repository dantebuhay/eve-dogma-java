package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class OperatorTargetOperation extends AbstractFittingOperation {
    public OperatorTargetOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result opTargetResult1 = evaluate(expr.getArg1(), model);
        Expression.Result opTargetResult2 = evaluate(expr.getArg2(), model);
        opTargetResult2.setOperator(opTargetResult1.getOperator());
        return opTargetResult2;
    }
}
