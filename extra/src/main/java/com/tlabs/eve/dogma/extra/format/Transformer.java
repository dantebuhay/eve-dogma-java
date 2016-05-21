package com.tlabs.eve.dogma.extra.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.Fitting;

final class Transformer {
    private Transformer() {}

    public static final List<Fitting> fromMapper(final InputStream in, ObjectMapper jsonMapper) throws IOException {
        final ObjectReader r = jsonMapper.readerFor(XFittings.class);
        final XFittings fittings = r.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(in);
        return transform(fittings);
    }

    private static List<Fitting> transform(final XFittings fittings) {
        if (null == fittings) {
            return Collections.emptyList();
        }

        final List<Fitting> returned = new ArrayList<>(fittings.getFittings().size());
        for (XFitting xf: fittings.getFittings()) {
            returned.add(transform(xf));
        }
        return returned;
    }

    protected static Fitting transform(final XFitting xf) {
        final Fitting fitting = new Fitting();
        fitting.setTypeName(xf.getShipType());
        fitting.setName(xf.getName());
        fitting.setDescription(xf.getDescription());
        for (int i = 0; i < xf.getHardware().size(); i++) {
            addHardware(fitting, xf.getHardware().get(i), i);
        }
        return fitting;
    }

    private static void addHardware(final Fitting fitting, final XHardware xh, final int index) {
        final Integer flag = flagOf(xh);
        if (null != flag) {
            fitting.setItem(xh.getType(), flag, Math.max(1, xh.getQuantity()));
        }
    }

    private static int flagOf(final XHardware xh) {
        if ("drone bay".equals(xh.getSlot())) {
            return Fitting.DRONES;
        }
        if (StringUtils.startsWith(xh.getSlot(), "hi slot")) {
           return flagOf(Attribute.FIT_HIGH_SLOTS, StringUtils.removeStart(xh.getSlot(), "hi slot"));
        }
        if (StringUtils.startsWith(xh.getSlot(), "med slot")) {
            return flagOf(Attribute.FIT_MEDIUM_SLOTS, StringUtils.removeStart(xh.getSlot(), "med slot"));
        }
        if (StringUtils.startsWith(xh.getSlot(), "low slot")) {
            return flagOf(Attribute.FIT_LOW_SLOTS, StringUtils.removeStart(xh.getSlot(), "low slot"));
        }
        if (StringUtils.startsWith(xh.getSlot(), "rig slot")) {
            return flagOf(Attribute.FIT_RIGS_SLOTS, StringUtils.removeStart(xh.getSlot(), "rig slot"));
        }
        if (StringUtils.startsWith(xh.getSlot(), "subsystem")) {
            return flagOf(Attribute.FIT_SUBSYSTEM_SLOTS, StringUtils.removeStart(xh.getSlot(), "subsystem"));
        }
        return -1;
    }

    private static int flagOf(final int slotID, final String index) {
        try {
            final int a = Integer.parseInt(index.trim());
            return Fitting.flagOf(slotID, a);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
}
