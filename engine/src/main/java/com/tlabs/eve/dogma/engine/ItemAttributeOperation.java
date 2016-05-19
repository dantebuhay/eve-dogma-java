package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

final class ItemAttributeOperation extends ItemModOperation {
    public ItemAttributeOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        Expression.Result itemAttributeDomain = evaluate(expr.getArg1(), model);
        FittingModel attributeModel = getAttributeModel((int)itemAttributeDomain.getValue(), model);
        if (attributeModel == null) {
            throw new IllegalStateException("null Attribute Model");
        }
        return evaluate(expr.getArg2(), attributeModel);
    }
}
