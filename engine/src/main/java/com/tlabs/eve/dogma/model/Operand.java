package com.tlabs.eve.dogma.model;

public class Operand {
    // Add two numbers to return a result
    public static final int Add = 1;

    public static final int AddGangGroupMod = 2;

    public static final int AddGangItemMod = 3;

    public static final int AddGangOwnSkillReqMod = 4;

    public static final int AddGangSkillReqMod = 5;

    public static final int AddItemMod = 6;

    public static final int AddLocGroupMod = 7;

    public static final int AddLocMod = 8;

    public static final int AddLocSkillReqMod = 9;

    // Logical AND operator
    public static final int And = 10;

    public static final int AddOwnSkillReqMod = 11;

    public static final int ItemAttribute = 12;

    public static final int Attack = 13;

    public static final int CargoScan = 14;

    public static final int CheatTeleDock = 15;

    public static final int CheatTeleGate = 16;

    // Executes two statements
    // Format: expression1; expression2
    public static final int Splice = 17;

    // Decrease attribute value by value of another attribute
    public static final int DecrementByAttribute = 18;

    public static final int AoeDecloak = 19;

    // Define operator, text in expressionValue field
    public static final int DefineOperator = 21;

    // Define operator, text in expressionValue field
    public static final int DefineAttribute = 22;

    // Define boolean constant, boolean in expressionValue field
    public static final int DefineBoolean = 23;

    // Define a domain (Char, Self, Ship)
    public static final int DefineLocation = 24;

    public static final int DefineGroup = 26;

    public static final int DefineInteger = 27;

    public static final int DefineType = 29;

    public static final int EcmBurst = 30;

    public static final int OperatorTarget = 31;

    public static final int AreaOfEffectDamage = 32;

    public static final int Eq = 33;

    public static final int GroupAttribute = 34;

    public static final int ItemAttributeCondition = 35;

    public static final int GetArg1Type = 36;

    public static final int Gt = 38;

    public static final int Gte = 39;

    public static final int GenericAttribute = 40;

    public static final int IfThen = 41;

    public static final int IncrementByAttributeValue = 42;

    public static final int MissileLaunch = 44;

    public static final int DefenderLaunch = 45;

    public static final int FofLaunch = 47;

    public static final int LocationGroup = 48; // domain.group

    public static final int LocationSkillReq = 49; // domain[skill req]

    public static final int Mine = 50;

    public static final int Or = 52; // Logical OR

    public static final int PowerBooster = 53;

    public static final int RmGangGroupMod = 54;

    public static final int RmGangItemMod = 55;

    public static final int RmGangOwnSkillReqMod = 56;

    public static final int RmGangSkillReqMod = 57;

    public static final int RemoveItemMod = 58;

    public static final int RmLocGroupMod = 59;

    public static final int RmLocMod = 60;

    public static final int RmLocSkillReqMod = 61;

    public static final int RmOwnSkillReqMod = 62;

    public static final int SkillReqAttr = 64;

    public static final int Assign = 65;

    public static final int ShipScan = 66;

    public static final int Subtract = 68;

    public static final int SurveyScan = 69;

    public static final int TargetHostile = 70;

    public static final int TargetSilent = 71;

    public static final int ToolTargetSkills = 72;

    public static final int UserError = 73;

    public static final int VerifyTargetGroup = 74;

    private int operandID;
    private String operandKey;

    private int resultCategoryID;
    private int arg1CategoryID;
    private int arg2CategoryID;

    private String description;
    private String format;

    public int getOperandID() {
        return operandID;
    }

    public void setOperandID(int operandID) {
        this.operandID = operandID;
    }

    public String getOperandKey() {
        return operandKey;
    }

    public void setOperandKey(String operandKey) {
        this.operandKey = operandKey;
    }

    public int getArg1CategoryID() {
        return arg1CategoryID;
    }

    public void setArg1CategoryID(int arg1CategoryID) {
        this.arg1CategoryID = arg1CategoryID;
    }

    public int getArg2CategoryID() {
        return arg2CategoryID;
    }

    public void setArg2CategoryID(int arg2CategoryID) {
        this.arg2CategoryID = arg2CategoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getResultCategoryID() {
        return resultCategoryID;
    }

    public void setResultCategoryID(int resultCategoryID) {
        this.resultCategoryID = resultCategoryID;
    }
}
