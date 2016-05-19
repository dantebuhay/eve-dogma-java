package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "dgmEffects")
public class EffectEntity {

    @Id
    @Column
    private int effectID;

    @Column
    private String effectName;

    @Column
    private String displayName;

    @Column
    private String description;

    @Column
    private int preExpression;

    @Column
    private int postExpression;

    public int getEffectID() {
        return effectID;
    }

    public String getEffectName() {
        return effectName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public int getPreExpression() {
        return preExpression;
    }

    public int getPostExpression() {
        return postExpression;
    }
}
