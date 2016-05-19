package com.tlabs.eve.dogma.model;

public class Effect {

    public static final int USES_LOW_SLOT = 11;
    public static final int USES_MEDIUM_SLOT = 13;
    public static final int USES_HIGH_SLOT = 12;
    public static final int USES_RIG_SLOT = 2663;
    public static final int USES_SUBSYSTEM = 3772;

    private long effectID;
    private String effectName;
    private String displayName;
    private String description;

    private Expression preExpression;
    private Expression postExpression;

    public long getEffectID() {
        return effectID;
    }

    public void setEffectID(long effectId) {
        this.effectID = effectId;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Expression getPreExpression() {
        return preExpression;
    }

    public void setPreExpression(Expression preExpression) {
        this.preExpression = preExpression;
    }

    public Expression getPostExpression() {
        return postExpression;
    }

    public void setPostExpression(Expression postExpression) {
        this.postExpression = postExpression;
    }
}
