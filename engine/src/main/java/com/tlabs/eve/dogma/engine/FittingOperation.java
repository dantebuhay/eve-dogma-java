package com.tlabs.eve.dogma.engine;

import com.tlabs.eve.dogma.model.Expression;

interface FittingOperation {

    Expression.Result execute(final Expression expr, final FittingModel model);

}
