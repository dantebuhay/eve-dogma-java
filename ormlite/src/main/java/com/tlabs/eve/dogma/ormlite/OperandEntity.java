package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "dgmOperands")
public class OperandEntity {
    @JsonProperty
    @Id
    @Column
    private int operandID;

    @JsonProperty
    @Column(unique = true)
    private String operandKey;

    @JsonProperty
    @Column
    private int resultCategoryID;

    @JsonProperty
    @Column
    private int arg1categoryID;

    @JsonProperty
    @Column
    private int arg2categoryID;

    @JsonProperty
    @Column
    private String description;

    @JsonProperty
    @Column
    private String format;

    @JsonProperty
    @Column
    private String pythonFormat;

    public int getOperandID() {
        return operandID;
    }

    public String getOperandKey() {
        return operandKey;
    }

    public int getArg1CategoryID() {
        return arg1categoryID;
    }

    public int getArg2CategoryID() {
        return arg2categoryID;
    }

    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }

    public String getPythonFormat() {
        return pythonFormat;
    }

    public int getResultCategoryID() {
        return resultCategoryID;
    }

}
