package com.tlabs.eve.dogma.model;

public class Expression {
    public static class Result {

        private final Double doubleValue;
        private Operator operator;

        public Result(final Operator operator) {
            this.operator = operator;
            this.doubleValue = null;
        }

        public Result() {
            this.operator = null;
            this.doubleValue = null;
        }

        public Result(final double value) {
            this.operator = null;
            this.doubleValue = value;
        }

        public void setOperator(Operator operator) {
            this.operator = operator;
        }

        public double getValue() {
            if (null == doubleValue) {
                throw new IllegalStateException("Requested an empty value.");
            }
            return doubleValue;
        }

        public boolean getBooleanValue() {
            if (null == doubleValue) {
                throw new IllegalStateException("Requested an empty value.");
            }
            return (this.doubleValue == 1);
        }

        public boolean isEmpty() {
            return (null == this.doubleValue);
        }

        public Operator getOperator() {
            return operator;
        }
    }

    private int expressionID;

    private String value;
    private String name;
    private String description;


    private Attribute attribute;

    private Operand operand;

    private Expression parent;

    private Expression arg1;
    private Expression arg2;

    public int getExpressionID() {
        return expressionID;
    }

    public void setExpressionID(int expressionID) {
        this.expressionID = expressionID;
    }

    public Operand getOperand() {
        return operand;
    }

    public void setOperand(Operand operand) {
        this.operand = operand;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Expression getArg1() {
        return arg1;
    }

    public void setArg1(Expression arg1) {
        this.arg1 = arg1;
        if (null != this.arg1) {
            this.arg1.setParent(this);
        }
    }

    public Expression getArg2() {
        return arg2;
    }

    public void setArg2(Expression arg2) {
        this.arg2 = arg2;
        if (null != this.arg2) {
            this.arg2.setParent(this);
        }
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Expression getParent() {
        return parent;
    }

    protected void setParent(final Expression parent) {
        this.parent = parent;
    }

}
