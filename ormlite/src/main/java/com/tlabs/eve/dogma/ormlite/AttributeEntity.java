package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "dgmAttributeTypes")
public class AttributeEntity {

    @Id
    @Column
    private int attributeID;

    @Column
    private String attributeName;

    @Column
    private String displayName;

    @Column
    private String description;

    @Column
    private float defaultValue;

    @Column
    private int unitID;

    @Column
    private int categoryID;

    @Column
    private boolean stackable;

    @Column
    private boolean highIsGood;

    @Column
    private int published;

    public int getAttributeID() {
        return attributeID;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getDescription() {
        return description;
    }

    public float getDefaultValue() {
        return defaultValue;
    }

    public int getUnitID() {
        return unitID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public boolean getStackable() {
        return stackable;
    }

    public boolean getHighIsGood() {
        return highIsGood;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPublished() {
        return published;
    }
}
