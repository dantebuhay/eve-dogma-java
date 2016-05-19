package com.tlabs.eve.dogma.model;

public class Attribute {

    public static final int MASS = 4;
    public static final int VOLUME = 161;

    public static final int STRUCTURE_HP = 9;
    public static final int STRUCTURE_HP_BONUS = 150;
    public static final int DRONE_CAPACITY = 283;
    public static final int DRONE_BANDWIDTH = 1271;
    public static final int DRONE_BANDWITH_USED = 1272;
    public static final int DRONE_BANDWITH_LOAD = 1273;

    public static final int DRONE_CONTROL_DISTANCE = 458;
    public static final int DRONE_RANGE_BONUS = 459;

    public static final int DAMAGE_EM = 114;
    public static final int DAMAGE_EXPLOSIVE = 116;
    public static final int DAMAGE_KINETIC = 117;
    public static final int DAMAGE_THERMAL = 118;

    public static final int STRUCTURE_INERTIA_MOD = 70;
    public static final int STRUCTURE_EM = 113;
    public static final int STRUCTURE_EXP = 111;
    public static final int STRUCTURE_KINETIC = 109;
    public static final int STRUCTURE_THERMAL = 110;
    public static final int STRUCTURE_EM_RES = 974;
    public static final int STRUCTURE_EXP_RES = 975;
    public static final int STRUCTURE_KINETIC_RES = 976;
    public static final int STRUCTURE_THERMAL_RES = 977;

    public static final int ARMOR_HP = 265;
    public static final int ARMOR_EM_RES = 267;
    public static final int ARMOR_EXP_RES = 268;
    public static final int ARMOR_KINETIC_RES = 269;
    public static final int ARMOR_THERMAL_RES = 270;

    public static final int SHIELD_HP = 263;
    public static final int SHIELD_HITPOINT_BONUS = 72;
    public static final int SHIELD_CAPACITY_BONUS = 337;
    public static final int SHIELD_RECHARGE = 479;
    public static final int SHIELD_EM_RES = 271;
    public static final int SHIELD_EXP_RES = 272;
    public static final int SHIELD_KINETIC_RES = 273;
    public static final int SHIELD_THERMAL_RES = 274;

    public static final int CAPACITOR_CAPACITY = 482;
    public static final int CAPACITOR_CAPACITY_BONUS = 147;
    public static final int CAPACITOR_RECHARGE = 55;
    public static final int CAPACITOR_NEED = 6;
    public static final int CAPACITOR_NEED_BONUS = 317;
    public static final int CAPACITOR_NEED_MULTIPLIER = 216;

    public static final int CPU_NEED = 50;
    public static final int CPU_LOAD = 49;
    public static final int CPU_CAPACITY = 48;
    public static final int CPU_NEED_MULTIPLIER = 202;//Factor to adjust module cpu need by.
    public static final int CPU_NEED_BONUS = 310;
    public static final int CPU_OUTPUT_BONUS = 424;

    public static final int POWER_NEED = 30;
    public static final int POWER_LOAD = 15;//Current load of power core
    public static final int POWER_CAPACITY = 11;
    public static final int POWER_OUTPUT_BONUS = 121;
    public static final int POWER_OUTPUT_MULTIPLIER = 145;//Multiplier to power core output.
    public static final int POWER_OUTPUT_ADD = 1378;

    public static final int TARGETING_RANGE = 76;
    public static final int TARGETING_TARGETS = 192;

    public static final int SCAN_RADAR_STRENGTH = 208;
    public static final int SCAN_LADAR_STRENGTH = 209;
    public static final int SCAN_MAGNOMETRIC_STRENGTH = 210;
    public static final int SCAN_GRAVIMETRIC_STRENGTH = 211;
    public static final int SCAN_RESOLUTION = 564;
    public static final int SCAN_RANGE_SHIP = 125;
    public static final int SCAN_RANGE_CARGO = 126;

    public static final int SIGNATURE_RADIUS = 552;

    //There is 2 actually; one with Camel Case name, one without - both %
    public static final int SIGNATURE_RADIUS_BONUS = 554;
    public static final int SIGNATURE_RADIUS_BONUS2 = 983;

    public static final int VELOCITY_MAX = 37;
    public static final int VELOCITY_MODIFIER = 1076;
    public static final int VELOCITY_WARP = 1281;

    public static final int PRIMARY_PILOT_ATTRIBUTE = 180;
    public static final int SECONDARY_PILOT_ATTRIBUTE = 181;

    public static final int SKILL_PRIMARY = 182;
    public static final int SKILL_PRIMARY_LEVEL = 277;

    public static final int SKILL_SECONDARY = 183;
    public static final int SKILL_SECONDARY_LEVEL = 278;

    public static final int SKILL_TERTIARY = 184;
    public static final int SKILL_TERTIARY_LEVEL = 279;

    public static final int TECH_LEVEL = 422;
    public static final int META_LEVEL = 633;

    public static final int SHIP_RESTRICTION = 1380;

    public static final int FIT_LOW_SLOTS = 12;
    public static final int FIT_MEDIUM_SLOTS = 13;
    public static final int FIT_HIGH_SLOTS = 14;
    //public static final int FIT_SUBSYSTEM_SLOTS = 3772;
    public static final int FIT_SUBSYSTEM_SLOTS = 1367;
    //public static final int FIT_RIGS_SLOTS = 1137;
    public static final int FIT_RIGS_SLOTS = 1154;

    public static final int FIT_LAUNCHERS = 101;
    public static final int FIT_TURRETS = 102;
    public static final int FIT_DRONES = 106;
    public static final int FIT_CARGO = 999999911;//made up by me
    // public static final int FIT_UPGRADES = 1154;//rigs/hardware hardpoints

    //Character related
    public static final int BONUS_CHARISMA = 175;
    public static final int BONUS_INTELLIGENCE = 176;
    public static final int BONUS_MEMORY = 177;
    public static final int BONUS_PERCEPTION = 178;
    public static final int BONUS_WILLPOWER = 179;


    public static final int SPECIAL_ORE_CAPACITY = 1556;

    private int attributeID;

    private String attributeName;

    private String description;

    private float defaultValue;
    private float currentValue;

    private int unitID;

    private int categoryID;

    private boolean stackable;

    private boolean highIsGood;

    public int getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(int attributeID) {
        this.attributeID = attributeID;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public boolean isHighIsGood() {
        return highIsGood;
    }

    public void setHighIsGood(boolean highIsGood) {
        this.highIsGood = highIsGood;
    }

}
