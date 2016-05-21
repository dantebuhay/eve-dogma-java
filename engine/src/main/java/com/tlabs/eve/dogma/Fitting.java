package com.tlabs.eve.dogma;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Item;

public final class Fitting implements Serializable {
    public static final int CARGO = 5;
    public static final int HANGAR = 90;
    public static final int SKILL = 7;

    public static final int LOW1 = 11;
    public static final int LOW2 = 12;
    public static final int LOW3 = 13;
    public static final int LOW4 = 14;
    public static final int LOW5 = 15;
    public static final int LOW6 = 16;
    public static final int LOW7 = 17;
    public static final int LOW8 = 18;

    public static final int MED1 = 19;
    public static final int MED2 = 20;
    public static final int MED3 = 21;
    public static final int MED4 = 22;
    public static final int MED5 = 23;
    public static final int MED6 = 24;
    public static final int MED7 = 25;
    public static final int MED8 = 26;

    public static final int HIGH1 = 27;
    public static final int HIGH2 = 28;
    public static final int HIGH3 = 29;
    public static final int HIGH4 = 30;
    public static final int HIGH5 = 31;
    public static final int HIGH6 = 32;
    public static final int HIGH7 = 33;
    public static final int HIGH8 = 34;

    public static final int RIG1 = 92;
    public static final int RIG2 = 93;
    public static final int RIG3 = 94;
    public static final int RIG4 = 95;
    public static final int RIG5 = 96;
    public static final int RIG6 = 97;
    public static final int RIG7 = 98;
    public static final int RIG8 = 99;

    public static final int SUB1 = 125;
    public static final int SUB2 = 126;
    public static final int SUB3 = 127;
    public static final int SUB4 = 128;
    public static final int SUB5 = 129;
    public static final int SUB6 = 130;
    public static final int SUB7 = 131;
    public static final int SUB8 = 132;

    public static final int SERVICE1 = 164;
    public static final int SERVICE2 = 165;
    public static final int SERVICE3 = 166;
    public static final int SERVICE4 = 167;
    public static final int SERVICE5 = 168;
    public static final int SERVICE6 = 169;
    public static final int SERVICE7 = 170;
    public static final int SERVICE8 = 171;

    public static final int OFFICE1 = 70;
    public static final int OFFICE2 = 71;
    public static final int OFFICE3 = 72;
    public static final int OFFICE4 = 73;
    public static final int OFFICE5 = 74;
    public static final int OFFICE6 = 75;
    public static final int OFFICE7 = 76;
    public static final int OFFICE8 = 77;
    public static final int OFFICE9 = 78;
    public static final int OFFICE10 = 79;
    public static final int OFFICE11 = 80;
    public static final int OFFICE12 = 81;
    public static final int OFFICE13 = 82;
    public static final int OFFICE14 = 83;
    public static final int OFFICE15 = 84;
    public static final int OFFICE16 = 85;

    public static final int FIXED = 35;
    public static final int CAPSULE = 56;
    public static final int PILOT = 57;

    public static final int TRAINING = 61;

    public static final int DRONES = 87;
    public static final int BOOSTER = 88;
    public static final int IMPLANT = 89;

    public static final Map<Integer, Integer[]> SLOTS;
    static {
        final HashMap<Integer, Integer[]> map = new HashMap<>();

        map.put(Attribute.FIT_LOW_SLOTS, new Integer[]{LOW1, LOW2, LOW3, LOW4, LOW5, LOW6, LOW7, LOW8});
        map.put(Attribute.FIT_MEDIUM_SLOTS, new Integer[]{MED1, MED2, MED3, MED4, MED5, MED6, MED7, MED8});
        map.put(Attribute.FIT_HIGH_SLOTS, new Integer[]{HIGH1, HIGH2, HIGH3, HIGH4, HIGH5, HIGH6, HIGH7, HIGH8});
        map.put(Attribute.FIT_SUBSYSTEM_SLOTS, new Integer[]{SUB1, SUB2, SUB3, SUB4, SUB5, SUB6, SUB7, SUB8});
        map.put(Attribute.FIT_RIGS_SLOTS, new Integer[]{RIG1, RIG2, RIG3, RIG4, RIG5, RIG6, RIG7, RIG8});

        SLOTS = Collections.unmodifiableMap(map);
    }

    private long id;//usually an index
    private String name;
    private String description;

    private long shipTypeID;
    private String shipTypeName;

    private final Map<Integer, String> items;//invflag->item
    private final Map<Integer, Long> quantities;//invflag->int

    public Fitting() {
        this.items = new HashMap<>();
        this.quantities = new HashMap<>();
    }

    public Fitting(final Fitting fitting) {
        this();

        this.id = fitting.getId();
        this.shipTypeID = fitting.shipTypeID;
        this.shipTypeName = fitting.shipTypeName;
        this.name = fitting.name;
        this.description = fitting.description;
        this.items.putAll(fitting.items);
        this.quantities.putAll(fitting.quantities);
    }

    public Fitting(final Item item) {
        this();

        this.shipTypeID = item.getItemID();
        this.shipTypeName = item.getItemName();
        this.name = item.getItemName();
    }

    public void removeItem(final int invFlag) {
        this.items.remove(invFlag);
        this.quantities.remove(invFlag);
    }

    public void setItem(final String item, final int invFlag) {
        setItem(item, invFlag, 1);
    }

    public void setItem(final String item, final int invFlag, final long quantity) {
        this.items.put(invFlag, item);
        this.quantities.put(invFlag, quantity);
    }

    public String getItem(final int invFlag) {
        return this.items.get(invFlag);
    }

    public long getQuantity(final int invFlag) {
        final Long a = this.quantities.get(invFlag);
        return (null == a) ? 0 : a.longValue();
    }

    public Map<Integer, String> getItems() {
        return Collections.unmodifiableMap(this.items);
    }

    public Map<Integer, String> getSlot(final int slotAttrID) {
        final Integer[] flags = flagsOf(slotAttrID);

        final Map<Integer, String> slots = new HashMap<>(10);

        for (Integer f: flags) {
            final String item = this.items.get(f);
            if (null != item) {
                slots.put(f, item);
            }
        }
        return Collections.unmodifiableMap(slots);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final String getTypeName() {
        return shipTypeName;
    }

    public final void setTypeName(String shipTypeName) {
        this.shipTypeName = shipTypeName;
    }

    public long getTypeID() {
        return shipTypeID;
    }

    public void setTypeID(long shipTypeID) {
        this.shipTypeID = shipTypeID;
    }

    public static Integer[] flagsOf(final int slotAttrID) {
        final Integer[] a = SLOTS.get(slotAttrID);
        return (null == a) ? new Integer[0] : a;
    }

    public static Integer flagOf(final int slotAttrID, final int index) {
        final Integer[] a = SLOTS.get(slotAttrID);
        if (ArrayUtils.isEmpty(a) || (index >= a.length)) {
            return null;
        }
        return a[index];
    }
}
