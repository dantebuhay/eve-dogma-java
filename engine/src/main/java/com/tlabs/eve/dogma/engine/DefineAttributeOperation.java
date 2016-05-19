package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class DefineAttributeOperation extends AbstractFittingOperation {

    public DefineAttributeOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        final int attributeId = expr.getAttribute().getAttributeID();
        final Double attributeValue = model.getAttributeValue(attributeId);
        if (null == attributeValue) {
            model.addAttribute(expr.getAttribute());
            return new Expression.Result(expr.getAttribute().getCurrentValue());
        }
        return new Expression.Result(attributeValue);
    }
}
