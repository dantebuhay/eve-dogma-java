package com.tlabs.eve.dogma.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tlabs.eve.dogma.model.Expression;

abstract class AbstractFittingOperation implements FittingOperation {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractFittingOperation.class);

    private final FittingEngine engine;

    protected AbstractFittingOperation(final FittingEngine engine) {
        this.engine = engine;
    }

    @Override
    public final Expression.Result execute(final Expression expr, final FittingModel model) {
        LOG.debug("execute: {} on {}", expr.getName(), model.getClass().getSimpleName());
        final Expression.Result r = apply(expr, model);
        return r;
    }

    abstract Expression.Result apply(final Expression expr, final FittingModel model);

    protected final Expression.Result evaluate(final Expression expr, final FittingModel model) {
        return this.engine.evaluate(expr, model);
    }
}
