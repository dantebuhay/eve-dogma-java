package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "invTraits")
public class TraitEntity {

    @Id
    @Column
    private int traitID;

    @Column
    private int typeID;

    @Column
    private int skillID;

    @Column
    private float bonus;

    @Column
    private String bonusText;

    @Column int unitID;

    public int getTraitID() {
        return traitID;
    }

    public int getTypeID() {
        return typeID;
    }

    public int getSkillID() {
        return skillID;
    }

    public float getBonus() {
        return bonus;
    }

    public String getBonusText() {
        return bonusText;
    }

    public int getUnitID() {
        return unitID;
    }
}
