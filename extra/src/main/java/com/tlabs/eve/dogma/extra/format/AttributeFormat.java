package com.tlabs.eve.dogma.extra.format;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tlabs.eve.dogma.Fitter;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Item;

public final class AttributeFormat {
    private static final Logger LOG = LoggerFactory.getLogger(AttributeFormat.class);

    private static final JexlEngine jexl = new JexlEngine();

    public static class JexlFunctions extends Object {

        public Number attr(final Item item, final int id) {
            final Attribute attr = item.getAttribute(id);
            if (null == attr) {
                return null;
            }
            final float value = attr.getCurrentValue();
            if (value == 0f) {
                return null;
            }
            return value;
        }

        public float floatValue(final Item item, final int id) {
            final Attribute attr = item.getAttribute(id);
            if (null == attr) {
                return 0.0f;
            }
            return attr.getCurrentValue();
        }

        public int intValue(final Item item, final int id) {
            final Attribute attr = item.getAttribute(id);
            if (null == attr) {
                return 0;
            }
            return (int)attr.getCurrentValue();
        }

        public String format(final Item item, final int id) {
            final Number attr = attr(item, id);
            final Format f = attributesFormat.get(id);
            if (null == f) {
                return (null == attr) ? null : attr.toString();
            }
            return f.format((null == attr) ? 0f: attr.floatValue());
        }
    }

    static {
        jexl.setCache(64);
        jexl.setLenient(false);
        jexl.setSilent(false);
        jexl.setFunctions(Collections.singletonMap("fit", (Object)new JexlFunctions()));
    }

    private interface Format {
        String format(final float attrValue);
    }

    private static final class UnitFormat implements Format {

        private final String unit;

        public UnitFormat(final String unit) {
            this.unit = unit;
        }

        @Override
        public String format(final float attrValue) {
            return attrValue + " " + unit;
        }
    }

    private static final Format defaultFormat = new Format() {
        @Override
        public String format(final float attrValue) {
            return Float.toString(attrValue);
        }
    };

    private static final Format integerFormat = new Format() {
        @Override
        public String format(final float attrValue) {
            return Integer.toString(Math.round(attrValue));
        }
    };

    private static final Format rangeFormat = new Format() {
        @Override
        public String format(final float attrValue) {
            return Integer.toString((int) (attrValue / 1000f)) + " Km";
        }
    };

    private static final Format secondFormat = new Format() {
        @Override
        public String format(final float attrValue) {
            return Integer.toString((int) (attrValue / 1000f)) + " s";
        }
    };

    private static final Format resistanceFormat = new Format() {
        @Override
        public String format(final float attrValue) {
            return Integer.toString((int) (100f - attrValue * 100f)) + " %";
        }
    };

    private static final Map<Integer, Format> attributesFormat;

    static {
        attributesFormat = new HashMap<>();
        attributesFormat.put(Attribute.FIT_HIGH_SLOTS, integerFormat);
        attributesFormat.put(Attribute.FIT_MEDIUM_SLOTS, integerFormat);
        attributesFormat.put(Attribute.FIT_LOW_SLOTS, integerFormat);
        attributesFormat.put(Attribute.FIT_RIGS_SLOTS, integerFormat);
        attributesFormat.put(Attribute.FIT_SUBSYSTEM_SLOTS, integerFormat);
        //attributesFormat.put(Attribute.FIT_UPGRADES, integerFormat);
        attributesFormat.put(Attribute.FIT_LAUNCHERS, integerFormat);
        attributesFormat.put(Attribute.FIT_TURRETS, integerFormat);
        attributesFormat.put(Attribute.FIT_DRONES, integerFormat);

        attributesFormat.put(Attribute.MASS, new UnitFormat("Kg"));
        attributesFormat.put(Attribute.VOLUME, new UnitFormat("m3"));

        attributesFormat.put(Attribute.CPU_CAPACITY, new UnitFormat("Tf"));
        attributesFormat.put(Attribute.CPU_LOAD, new UnitFormat("Tf"));
        attributesFormat.put(Attribute.CPU_NEED, new UnitFormat("Tf"));

        attributesFormat.put(Attribute.POWER_CAPACITY, new UnitFormat("MW"));
        attributesFormat.put(Attribute.POWER_LOAD, new UnitFormat("MW"));
        attributesFormat.put(Attribute.POWER_NEED, new UnitFormat("MW"));

        attributesFormat.put(Attribute.STRUCTURE_HP, new UnitFormat("HP"));
        attributesFormat.put(Attribute.DRONE_CAPACITY, new UnitFormat("m3"));
        attributesFormat.put(Attribute.DRONE_BANDWIDTH, new UnitFormat("Mbit/sec"));
        attributesFormat.put(Attribute.DRONE_BANDWITH_USED, new UnitFormat("Mbit/sec"));
        attributesFormat.put(Attribute.DRONE_BANDWITH_LOAD, new UnitFormat("Mbit/sec"));
        attributesFormat.put(Attribute.DRONE_CONTROL_DISTANCE, new UnitFormat("Km"));

        attributesFormat.put(Attribute.STRUCTURE_INERTIA_MOD, new UnitFormat("x"));
        attributesFormat.put(Attribute.STRUCTURE_EM, resistanceFormat);
        attributesFormat.put(Attribute.STRUCTURE_EXP, resistanceFormat);
        attributesFormat.put(Attribute.STRUCTURE_KINETIC, resistanceFormat);
        attributesFormat.put(Attribute.STRUCTURE_THERMAL, resistanceFormat);

        attributesFormat.put(Attribute.ARMOR_HP, new UnitFormat("HP"));
        attributesFormat.put(Attribute.ARMOR_EM_RES, resistanceFormat);
        attributesFormat.put(Attribute.ARMOR_EXP_RES, resistanceFormat);
        attributesFormat.put(Attribute.ARMOR_KINETIC_RES, resistanceFormat);
        attributesFormat.put(Attribute.ARMOR_THERMAL_RES, resistanceFormat);

        attributesFormat.put(Attribute.SHIELD_HP, new UnitFormat("HP"));
        attributesFormat.put(Attribute.SHIELD_RECHARGE, secondFormat);
        attributesFormat.put(Attribute.SHIELD_EM_RES, resistanceFormat);
        attributesFormat.put(Attribute.SHIELD_EXP_RES, resistanceFormat);
        attributesFormat.put(Attribute.SHIELD_KINETIC_RES, resistanceFormat);
        attributesFormat.put(Attribute.SHIELD_THERMAL_RES, resistanceFormat);

        attributesFormat.put(Attribute.CAPACITOR_CAPACITY, new UnitFormat("Gj"));
        attributesFormat.put(Attribute.CAPACITOR_RECHARGE, secondFormat);

        attributesFormat.put(Attribute.TARGETING_RANGE, rangeFormat);
        attributesFormat.put(Attribute.TARGETING_TARGETS, new UnitFormat("x"));
        attributesFormat.put(Attribute.SCAN_GRAVIMETRIC_STRENGTH, new UnitFormat("points"));
        attributesFormat.put(Attribute.SCAN_LADAR_STRENGTH, new UnitFormat("points"));
        attributesFormat.put(Attribute.SCAN_RADAR_STRENGTH, new UnitFormat("points"));
        attributesFormat.put(Attribute.SCAN_MAGNOMETRIC_STRENGTH, new UnitFormat("points"));
        attributesFormat.put(Attribute.SCAN_RESOLUTION, new UnitFormat("mm"));
        attributesFormat.put(Attribute.SIGNATURE_RADIUS, new UnitFormat("m"));

        attributesFormat.put(Attribute.VELOCITY_MAX, new UnitFormat("m/s"));
        attributesFormat.put(Attribute.VELOCITY_WARP, new UnitFormat("AU/s"));

    }

    private AttributeFormat() {
    }

    public static String format(final long attrID, final float attrValue) {
        final Format format = attributesFormat.get(Integer.valueOf((int)attrID));
        if (null == format) {
            return defaultFormat.format(attrValue);
        }
        return format.format(attrValue);
    }

    public static String format(final Attribute attribute) {
        if (null == attribute) {
            return "0";
        }
        return format(attribute.getAttributeID(), attribute.getCurrentValue());
    }

    public static <T> T evaluate(final Fitter fit, final String attributeExpression) {
        return jexl(fit, replaceAttributes(attributeExpression));
    }

    public static <T> T evaluate(final Item item, final String attributeExpression) {
        return jexl(item, replaceAttributes(attributeExpression));
    }

    public static <T> T jexl(final Fitter fit, final String jexlExpression) {
        final JexlContext context = new MapContext();

        context.set("fit", fit);
        context.set("item", fit.getItem());
        return jexl(context, jexlExpression);
    }

    public static <T> T jexl(final Item item, final String jexlExpression) {
        final JexlContext context = new MapContext();
        context.set("item", item);
        return jexl(context, jexlExpression);
    }


    private static <T> T jexl(final JexlContext context, final String jexlExpression) {
        try {
            final Expression e = jexl.createExpression(jexlExpression);
            final Object result = e.evaluate(context);
            return (T)result;
        }
        catch (RuntimeException e) {
            LOG.debug(e.getLocalizedMessage(), e);
            LOG.error(e.getLocalizedMessage());
            return null;
        }
    }

    private static String replaceAttributes(final String expression) {
        final String replaced = replaceAttributeValues(expression);
        final String[] or = StringUtils.split(replaced, "|");
        if ((ArrayUtils.isEmpty(or) || or.length == 1)) {
            return replaced;
        }

        String returned = "";
        for (int i = 0; i < or.length; i++) {
            String s = "if (null != " + or[i] + ") {return " + or[i] + "}";
            if (i != 0) {
                s = " else " + s;
            }
            returned = returned + s;
        }

        return returned;
    }

    private static String replaceAttributeValues(final String expression) {
        final String[] split = StringUtils.substringsBetween(expression, "{", "}");
        if (ArrayUtils.isEmpty(split)) {
            return expression;
        }

        String returned = expression;
        for (String s: split) {
            if (s.endsWith("u")) {
                returned = StringUtils.replace(returned, "{" + s + "}", "fit:format(item, " + StringUtils.removeEnd(s, "u") + ")");
            }
            else if (s.endsWith("f")) {
                returned = StringUtils.replace(returned, "{" + s + "}", "fit:floatValue(item, " + StringUtils.removeEnd(s, "f") + ")");
            }
            else if (s.endsWith("i")) {
                returned = StringUtils.replace(returned, "{" + s + "}", "fit:intValue(item, " + StringUtils.removeEnd(s, "i") + ")");
            }
            else {
                returned = StringUtils.replace(returned, "{" + s + "}", "fit:attr(item, " + s + ")");
            }
        }
        return returned;
    }
}
