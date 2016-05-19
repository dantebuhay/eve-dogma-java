package com.tlabs.eve.dogma.model;

import java.util.List;
import java.util.Map;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Effect;

public class Item {

    public static final int SHIP = 6;
    public static final int MODULE = 7;
    public static final int CHARGE = 8;
    public static final int BONUS = 14;
    public static final int SKILL = 16;
    public static final int DRONE = 18;
    public static final int IMPLANT = 20;
    public static final int SUBSYSTEM = 32;
    public static final int EFFECT = 53;

    private int itemID;
    private String itemName;
    private String description;

    private int groupID;
    private String groupName;

    private int categoryID;
    private String categoryName;

    private int marketGroupID;
    private String marketGroupName;

    private Map<Integer, Attribute> attributes;
    private List<Effect> effects;
    private List<Trait> traits;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Map<Integer, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Integer, Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public Effect getEffect(final long effectID) {
        for (Effect e: this.effects) {
            if (e.getEffectID() == effectID) {
                return e;
            }
        }
        return null;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public int getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(int marketGroupID) {
        this.marketGroupID = marketGroupID;
    }

    public String getMarketGroupName() {
        return marketGroupName;
    }

    public void setMarketGroupName(String marketGroupName) {
        this.marketGroupName = marketGroupName;
    }

    public Attribute getAttribute(final int attributeID) {
        return this.attributes.get(attributeID);
    }

    public Attribute getAttribute(final String attributeName) {
        for (Map.Entry<Integer, Attribute> e: this.attributes.entrySet()) {
            if (e.getValue().getAttributeName().equals(attributeName)) {
                return e.getValue();
            }
        }
        return null;
    }

}
