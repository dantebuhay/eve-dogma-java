package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "dgmTypeAttributes")
public class TypeAttributeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private int id;

    @Column
    private int typeID;

    @Column
    private int attributeID;

    @Column
    private Integer valueInt;

    @Column
    private Float valueFloat;

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public long getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(int attributeID) {
        this.attributeID = attributeID;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    public Float getValueFloat() {
        return valueFloat;
    }

    public void setValueFloat(Float valueFloat) {
        this.valueFloat = valueFloat;
    }
}
