package com.tlabs.eve.dogma.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tlabs.eve.dogma.model.Effect;
import com.tlabs.eve.dogma.model.Expression;
import com.tlabs.eve.dogma.model.Item;

public final class FittingEngine {

    private static final Logger LOG = LoggerFactory.getLogger(FittingEngine.class);

    private final Map<Integer, FittingOperation> operations;

    public interface Listener {

        void onExecute(
                final FittingOperation operation,
                final FittingModel target);

        void onResult(
                final FittingOperation operation,
                final FittingModel target,
                final Expression.Result result);
    }

    public static class Domain {
        public static final int Self = 1;

        public static final int Char = 2;

        public static final int Ship = 3;

        public static final int Target = 4;

        public static final int Area = 5;

        public static final int Other = 6;

        public static int from(String value) {
            if ("Self".equals(value)) {
                return Self;
            }
            if ("Char".equals(value)) {
                return Char;
            }
            if ("Ship".equals(value)) {
                return Ship;
            }
            if ("Target".equals(value)) {
                return Target;
            }
            if ("Area".equals(value)) {
                return Area;
            }
            if ("Other".equals(value)) {
                return Other;
            }

            return -1;
        }
    }

    private final boolean validating;
    private Listener listener;

    public FittingEngine() {
        this(true);
    }

    public FittingEngine(final boolean validating) {
        super();
        this.validating = validating;

        this.operations = new HashMap<>();
        this.operations.put(1, new AddOperation(this));
        this.operations.put(6, new AddItemModOperation(this));
        this.operations.put(9, new AddLocationRequiredSkillOperation(this));
        this.operations.put(10, new AndOperation(this));
        this.operations.put(12, new ItemAttributeOperation(this));
        this.operations.put(17, new SpliceOperation(this));
        this.operations.put(18, new DecrementByAttributeOperation(this));
        this.operations.put(21, new DefineOperatorOperation(this));
        this.operations.put(22, new DefineAttributeOperation(this));
        this.operations.put(23, new DefineBooleanOperation(this));
        this.operations.put(24, new DefineLocationOperation(this));
        this.operations.put(27, new DefineIntegerOperation(this));
        this.operations.put(31, new OperatorTargetOperation(this));
        this.operations.put(33, new EqualsOperation(this));
        this.operations.put(35, new ItemAttributeConditionOperation(this));
        this.operations.put(38, new GreaterOperation(this));
        this.operations.put(39, new GreaterEqualsOperation(this));
        this.operations.put(41, new IfThenOperation(this));
        this.operations.put(42, new IncrementByAttributeOperation(this));
        this.operations.put(52, new OrOperation(this));
        this.operations.put(58, new RemoveItemModOperation(this));
        this.operations.put(65, new SetItemAttributeOperation(this));
        this.operations.put(67, new SkillCheckOperation(this));
        this.operations.put(68, new SubstractOperation(this));
        this.operations.put(72, new CheckToolTargetSkillsOperation(this));
        this.operations.put(73, new RaiseErrorOperation(this));
        this.operations.put(74, new VerifyTargetGroupOperation(this));
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public final FittingModel.Ship fit(final FittingModel.Ship ship, final Item module) {
        for (Effect effect: module.getEffects()) {
            final Expression pre = effect.getPreExpression();
            evaluate(pre, new FittingModel.Module(ship, module));
        }
        return ship;
    }

    public final FittingModel.Ship unfit(final FittingModel.Ship ship, final Item module) {
        for (Effect effect: module.getEffects()) {
            final Expression post = effect.getPostExpression();
            evaluate(post, new FittingModel.Module(ship, module));
        }
        return ship;
    }

    Expression.Result evaluate(final Expression expr, final FittingModel model) {
        final FittingOperation operation = this.operations.get(expr.getOperand().getOperandID());
        if (null == operation) {
            final String error = "Expression Operand: " + expr.getOperand().getOperandID() + ":" + ToStringBuilder.reflectionToString(expr);
            if (this.validating) {
                throw new UnsupportedOperationException(error);
            }
            LOG.warn(error);
            return new Expression.Result(0);
        }
        if (null != listener) {
            listener.onExecute(operation, model);
        }
        final Expression.Result result = operation.execute(expr, model);
        if (null != listener) {
            listener.onResult(operation, model, result);
        }
         return result;
    }

}
