package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "dgmTypes")//it's a view
public class TypeEntity {

    @Id
    @Column
    private int typeID;

    @Column
    private String description;

    @Column(unique = true)
    private String typeName;

    @Column
    private int groupID;

    @Column
    private String groupName;

    @Column
    private int categoryID;

    @Column
    private String categoryName;

    @Column
    private int marketGroupID;

    @Column
    private String marketGroupName;

    @Column
    private int iconID;

    @Column
    private int raceID;

    @Column
    private double mass;

    @Column
    private double volume;

    @Column
    private double capacity;

    @Column
    private double portionSize;

    @Column
    private double basePrice;

    @Column
    private int published;

    public int getTypeID() {
        return typeID;
    }

    public int getGroupID() {
        return groupID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }

    public int getMarketGroupID() {
        return marketGroupID;
    }

    public int getIconID() {
        return iconID;
    }

    public int getRaceID() {
        return raceID;
    }

    public double getMass() {
        return mass;
    }

    public double getVolume() {
        return volume;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getPortionSize() {
        return portionSize;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getPublished() {
        return published;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getMarketGroupName() {
        return marketGroupName;
    }
}
