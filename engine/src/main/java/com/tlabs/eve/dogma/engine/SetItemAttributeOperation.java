package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class SetItemAttributeOperation extends AbstractFittingOperation {

    public SetItemAttributeOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        //model.setAttributeValue(Long.parseLong(expr.getArg1().getValue()), Double.parseDouble(expr.getArg2().getValue()));
        Expression.Result r1 = evaluate(expr.getArg1(), model);
        Expression.Result r2 = evaluate(expr.getArg2(), model);
        return new Expression.Result(1);
    }
}
