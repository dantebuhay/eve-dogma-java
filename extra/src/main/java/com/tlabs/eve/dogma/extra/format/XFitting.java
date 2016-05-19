package com.tlabs.eve.dogma.extra.format;

import java.util.LinkedList;
import java.util.List;


class XFitting {

    private String description;

    private String name;

    private String shipType;

    private List<XHardware> hardware = new LinkedList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public List<XHardware> getHardware() {
        return hardware;
    }

    public void setHardware(List<XHardware> hardware) {
        this.hardware = hardware;
    }
}
