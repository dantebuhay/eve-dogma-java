package com.tlabs.eve.dogma;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.tlabs.eve.dogma.Fitter.Module;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Effect;
import com.tlabs.eve.dogma.model.Item;

final class FitterHelper {


    static int findSlot(final Item item) {
        if (null != item.getEffect(Effect.USES_HIGH_SLOT)) {
            return Attribute.FIT_HIGH_SLOTS;
        }
        if (null != item.getEffect(Effect.USES_LOW_SLOT)) {
            return Attribute.FIT_LOW_SLOTS;
        }
        if (null != item.getEffect(Effect.USES_MEDIUM_SLOT)) {
            return Attribute.FIT_MEDIUM_SLOTS;
        }
        if (null != item.getEffect(Effect.USES_RIG_SLOT)) {
            return Attribute.FIT_RIGS_SLOTS;
        }
        if (null != item.getEffect(Effect.USES_SUBSYSTEM)) {
            return Attribute.FIT_SUBSYSTEM_SLOTS;
        }
        return -1;
    }

    static Map<Integer, Module> createInventory(final Item with) {
        final Map<Integer, Module> inventory = new LinkedHashMap<>();

        for (Map.Entry<Integer, Integer[]> slot: Fitting.SLOTS.entrySet()) {
            final Attribute attr = with.getAttribute(slot.getKey());
            if (null == attr) {
                continue;
            }

            final int slotCount = Math.min((int)attr.getCurrentValue(), slot.getValue().length);
            for (int i = 0; i < slotCount; i++) {
                final Module module = new Module(slot.getKey(), slot.getValue()[i]);
                module.setItem(null);
                module.setQuantity(0);

                inventory.put(module.getInventoryID(), module);
            }
        }
        return Collections.unmodifiableMap(inventory);
    }

    static int findAvailableModule(final Map<Integer, Module> inventory, final Item forItem) {
        final int slotAttr = findSlot(forItem);
        if (-1 == slotAttr) {
            return -1;
        }
        for (Module m: inventory.values()) {
            if ((slotAttr == m.getSlotID()) && (null == m.getItem())) {
                return m.getInventoryID();
            }
        }
        return -1;
    }


    static List<Module> getSlotInventory(final Map<Integer, Module> inventory, int slotAttrID) {
        final List<Module> list = new LinkedList<>();
        for (Module m: inventory.values()) {
            if (m.getSlotID() == slotAttrID) {
                list.add(m);
            }
        }
        return Collections.unmodifiableList(list);
    }

    static List<Module> getAvailableModules(final Map<Integer, Module> inventory) {
        final List<Module> list = new LinkedList<>();
        for (Module m: inventory.values()) {
            if (m.getItem() == null) {
                list.add(m);
            }
        }
        return Collections.unmodifiableList(list);
    }

    static List<Module> getAvailableModules(final Map<Integer, Module> inventory, final int slotAttrID) {
        final List<Module> list = new LinkedList<>();
        for (Module m: inventory.values()) {
            if ((m.getSlotID() == slotAttrID) && (m.getItem() == null)) {
                list.add(m);
            }
        }
        return Collections.unmodifiableList(list);
    }

    static List<Module> getUsedModules(final Map<Integer, Module> inventory) {
        final List<Module> list = new LinkedList<>();
        for (Module m: inventory.values()) {
            if (m.getItem() != null) {
                list.add(m);
            }
        }
        return Collections.unmodifiableList(list);
    }

    static List<Module> getUsedModules(final Map<Integer, Module> inventory, final int slotAttrID) {
        final List<Module> list = new LinkedList<>();
        for (Module m: inventory.values()) {
            if ((m.getSlotID() == slotAttrID) && (m.getItem() != null)) {
                list.add(m);
            }
        }
        return Collections.unmodifiableList(list);
    }

    public static boolean isFull(final Map<Integer, Module> inventory) {
        for (Module m: inventory.values()) {
            if (m.getItem() == null) {
                return false;
            }
        }
        return true;
    }
}
