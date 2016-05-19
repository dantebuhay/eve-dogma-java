package com.tlabs.eve.dogma.ormlite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "invFlags")
public class InventoryFlagEntity {

    @Id
    @Column
    private int flagID;

    @Column
    private int orderID;

    @Column
    private String flagName;

    @Column
    private String flagText;

    public int getFlagID() {
        return flagID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getFlagName() {
        return flagName;
    }

    public String getFlagText() {
        return flagText;
    }
}
