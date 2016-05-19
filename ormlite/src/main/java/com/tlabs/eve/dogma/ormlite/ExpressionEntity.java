package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "dgmExpressions")
public class ExpressionEntity {

    @Id
    @Column
    private int expressionID;

    @Column
    private int operandID;

    @Column
    private int arg1;

    @Column
    private int arg2;

    @Column
    private String expressionValue;

    @Column
    private String description;

    @Column
    private String expressionName;

    @Column
    private int expressionTypeID;

    @Column
    private int expressionGroupID;

    @Column
    private int expressionAttributeID;

    public int getExpressionID() {
        return expressionID;
    }

    public int getOperandID() {
        return operandID;
    }

    public int getArg1() {
        return arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public String getDescription() {
        return description;
    }

    public String getExpressionName() {
        return expressionName;
    }

    public int getExpressionTypeID() {
        return expressionTypeID;
    }

    public int getExpressionGroupID() {
        return expressionGroupID;
    }

    public int getExpressionAttributeID() {
        return expressionAttributeID;
    }
}
