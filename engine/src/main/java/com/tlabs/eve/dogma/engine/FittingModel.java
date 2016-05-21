package com.tlabs.eve.dogma.engine;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Item;

//FIXME fix this class and its weird Module that depends on a Ship that are also FittingModel(s).
public class FittingModel {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractFittingOperation.class);

    public static final class Ship extends FittingModel {
        public Ship(Item item) {
            super(item);
        }
    }

    static final class Module extends FittingModel {
        private Ship ship;//FIXME this is wrong
        public Module(Ship ship, Item item) {
            super(item);
            this.ship = ship;
        }

        public Ship getShip() {
            return ship;
        }
    }

    private final Item item;

    protected FittingModel(Item item) {
        Validate.notNull(item);
        this.item = item;
    }

    public final Item getItem() {
        return item;
    }

    public final void setAttributeValue(final int attrID, final double attrValue) {
        final Attribute attr = this.item.getAttribute(attrID);

        if (null == attr) {
            LOG.debug("setAttributeValue unknown attrID={}", attrID);
            return;
        }

        LOG.debug("setAttributeValue {}-{}={}", attr.getAttributeID(), attr.getAttributeName(), attrValue);
        attr.setCurrentValue((float)attrValue);
    }

    public final Double getAttributeValue(final int attrID) {
        final Attribute attr = this.item.getAttribute(attrID);
        if (null == attr) {
            LOG.debug("getAttribute unknown attrID={}", attrID);
            return null;
        }
        return (double)attr.getCurrentValue();
    }

    public final Double getAttributeValue(final String attrName) {
        final Attribute attr = this.item.getAttribute(attrName);
        if (null == attr) {
            LOG.debug("getAttribute unknown attrName={}", attrName);
            return null;
        }
        return (double)attr.getCurrentValue();
    }

    public final Attribute getAttribute(final int attrID) {
        final Attribute attr = this.item.getAttribute(attrID);
        if (null == attr) {
            LOG.debug("getAttribute unknown attrID={}", attrID);
            return null;
        }
        return attr;
    }

    public final Attribute getAttribute(final String attrName) {
        final Attribute attr = this.item.getAttribute(attrName);
        if (null == attr) {
            LOG.debug("getAttribute unknown attrName={}", attrName);
            return null;
        }
        return attr;
    }

    public void addAttribute(final Attribute attribute) {
        LOG.debug("addAttribute {}={}", attribute.getAttributeName(), attribute.getCurrentValue());
        this.item.getAttributes().put(attribute.getAttributeID(), attribute);
    }

    public Map<Integer, Attribute> getAttributes() {
        return this.item.getAttributes();
    }
}
