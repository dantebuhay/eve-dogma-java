package com.tlabs.eve.dogma.engine;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.tlabs.eve.dogma.model.Expression;

final class RaiseErrorOperation extends AbstractFittingOperation {

    public RaiseErrorOperation(FittingEngine engine) {
        super(engine);
    }

    @Override
    Expression.Result apply(Expression expr, FittingModel model) {
        //Expression.Result r = evaluate(expr.getParent(), model);
        final String value = expr.getValue();
        if (null == value) {
            LOG.warn("RaiseErrorOperation: " + ToStringBuilder.reflectionToString(expr));
            return new Expression.Result(0);
        }
        throw new IllegalStateException(ToStringBuilder.reflectionToString(expr));
       // LOG.warn("RaiseErrorOperation not implemented: expr=" + ToStringBuilder.reflectionToString(expr));
       // return new Expression.Result(1);//TODO
    }
}
