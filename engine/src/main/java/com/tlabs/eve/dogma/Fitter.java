package com.tlabs.eve.dogma;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import com.tlabs.eve.dogma.engine.FittingEngine;
import com.tlabs.eve.dogma.engine.FittingModel;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Item;

public final class Fitter {

    public static final class Module {
        private final int inventoryID;//inv flag
        private final int slotID;//attr slot id

        private Item item;

        private long quantity = 1;//usually 1

        public Module(int slotID, int inventoryID) {
            this.slotID = slotID;
            this.inventoryID = inventoryID;
        }

        public Item getItem() {
            return this.item;
        }

        public int getInventoryID() {
            return inventoryID;
        }

        public long getQuantity() {
            return quantity;
        }

        public int getSlotID() {
            return slotID;
        }

        public Attribute getAttribute(final int attributeID) {
            return (null == this.item) ? null : this.item.getAttribute(attributeID);
        }

        public float getAttributeValue(final int attributeID) {
            final Attribute attr = getAttribute(attributeID);
            return (null == attr) ? 0.0f : attr.getCurrentValue();
        }

        public float getAttributeDefault(final int attributeID) {
            final Attribute attr = getAttribute(attributeID);
            return (null == attr) ? 0.0f : attr.getDefaultValue();
        }

        public float getAttributeValue(final String attributeName) {
            final Attribute attr = getAttribute(attributeName);
            return (null == attr) ? 0.0f : attr.getCurrentValue();
        }

        public float getAttributeDefault(final String attributeName) {
            final Attribute attr = getAttribute(attributeName);
            return (null == attr) ? 0.0f : attr.getDefaultValue();
        }

        public Attribute getAttribute(final String attributeName) {
            return (null == this.item) ? null : getAttribute(attributeName);
        }

        void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        void setItem(Item item) {
            this.item = item;
        }
    }

    public static final class Builder {
        private final FittingProvider provider;

        public Builder(FittingProvider provider) {
            this.provider = provider;
        }

        public Fitter build(final String from) {
            return build(this.provider.findItem(from));
        }

        public Fitter build(final long from) {
            return build(this.provider.findItem(from));
        }

        public Fitter build(final Item from) {
            Validate.notNull(from);
            return build(new Fitting(from));
        }

        public Fitter build(final Fitting from) {
            return new Fitter(this.provider, from);
        }
    }

    private static final FittingEngine engine = new FittingEngine(false);

    private final FittingProvider provider;
    private Map<Integer, Module> inventory;//inv flags->modules (usually 1)

    private Fitting fitting;
    private FittingModel.Ship model;

    private Fitter(final FittingProvider provider, final Fitting fitting) {
        Validate.notNull(provider);
        Validate.notNull(fitting);

        this.provider = provider;
        refit(fitting);
    }

    public long getFittingId() {
        return this.fitting.getId();
    }

    public String getName() {
        return this.fitting.getName();
    }

    public Fitter setName(String name) {
        this.fitting.setName(name);
        return this;
    }

    public String getDescription() {
        return this.fitting.getDescription();
    }

    public Fitter setDescription(String description) {
        this.fitting.setDescription(description);
        return this;
    }

    public String getTypeName() {
        return this.fitting.getTypeName();
    }

    public long getTypeID() {
        return this.model.getItem().getItemID();
    }

    public double getAttributeValue(final String attributeName) {
        final Double d = this.model.getAttributeValue(attributeName);
        return (null == d) ? 0d : d;
    }

    public double getAttributeValue(final int attributeID) {
        final Double d = this.model.getAttributeValue(attributeID);
        return (null == d) ? 0d : d;
    }

    public Attribute getAttribute(final String attributeName) {
        return this.model.getAttribute(attributeName);
    }

    public Attribute getAttribute(final int attributeID) {
        return this.model.getAttribute(attributeID);
    }

    public Map<Integer, Attribute> getAttributes() {
        return this.model.getAttributes();
    }

    public Item getItem() {
        return this.model.getItem();
    }

    public Map<Integer, Module> getModules() {
        return this.inventory;
    }

    public List<Module> getModules(int slotAttrID) {
        return FitterHelper.getSlotInventory(this.inventory, slotAttrID);
    }

    public List<Module> getAvailableModules() {
        return FitterHelper.getAvailableModules(this.inventory);
    }

    public List<Module> getUsedModules() {
        return FitterHelper.getUsedModules(this.inventory);
    }

    public List<Module> getAvailableModules(final int slotAttrID) {
        return FitterHelper.getAvailableModules(this.inventory, slotAttrID);
    }

    public List<Module> getUsedModules(final int slotAttrID) {
        return FitterHelper.getUsedModules(this.inventory, slotAttrID);
    }

    public boolean isFull() {
        return FitterHelper.isFull(this.inventory);
    }

    public Module fit(final String item) {
        return fit(this.provider.findItem(item));
    }

    public Module fit(final long item) {
        return fit(this.provider.findItem(item));
    }

    public Module fit(final Item item) {
        return fit(item, FitterHelper.findAvailableModule(this.inventory, item));
    }

    public Module fit(final Item item, final int inventoryID) {
        return fitImpl(item, inventoryID, true);
    }

    public boolean unfit(final Module module) {
        return unfitImpl(module, true);
    }

    public void unfit() {
        final Fitting newFitting = new Fitting(
                this.provider.findItem(this.model.getItem().getItemID()));
        newFitting.setName(this.fitting.getName());
        newFitting.setDescription(this.fitting.getDescription());
        refit(newFitting);
    }

    public Fitting toFitting() {
        return new Fitting(this.fitting);
    }

    private Module fitImpl(final Item item, final int inventoryID, final boolean addToFitting) {
        if (null == item) {
            return null;
        }
        if (-1 == inventoryID) {
            return null;
        }

        final Module existing = this.inventory.get(inventoryID);
        if ((null == existing) || (null != existing.getItem())) {
            return null;
        }

        if (null == engine.fit(this.model, item)) {
            return null;
        }

        existing.setItem(item);
        existing.setQuantity(1);

        if (addToFitting) {
            this.fitting.setItem(item.getItemName(), existing.getInventoryID());
        }
        return existing;
    }

    private boolean unfitImpl(final Module module, final boolean removeFromFitting) {
        if (null == module) {
            return false;
        }

        final Module existing = this.inventory.get(module.getInventoryID());
        if ((null == existing) || (existing.getItem() == null)) {
            return false;
        }

        for (int i = 0; i < existing.getQuantity(); i++) {
            engine.unfit(this.model, existing.getItem());
        }

        existing.setItem(null);
        existing.setQuantity(0);
        if (removeFromFitting) {
            this.fitting.removeItem(existing.getInventoryID());
        }
        return true;
    }

    private void refit(final Fitting fitting) {
        Validate.notNull(fitting);

        final Item ship = this.provider.findItem(fitting.getTypeName());
        Validate.notNull(ship, fitting.getTypeName());

        this.fitting = fitting;
        this.model = new FittingModel.Ship(ship);

        final Map<Integer, Module> inventory = FitterHelper.createInventory(ship);
        for (Map.Entry<Integer, String> fit: fitting.getItems().entrySet()) {
            final Module module = inventory.get(fit.getKey());
            if (null == module) {
                continue;
            }

            final Item item = this.provider.findItem(fit.getValue());
            if (null == item) {
                continue;
            }
            module.setItem(item);
            module.setQuantity(fitting.getQuantity(fit.getKey()));
            for (int i = 0; i < module.getQuantity(); i++) {
                engine.fit(this.model, item);
            }
        }

        this.inventory = inventory;
    }

}
