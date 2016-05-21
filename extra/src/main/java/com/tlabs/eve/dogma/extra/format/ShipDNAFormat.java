package com.tlabs.eve.dogma.extra.format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.Fitting;

final class ShipDNAFormat {

    public static final List<Fitting> fromDNAContent(final InputStream in) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return fromDNAContent(reader);
    }

    public static void toDNAContent(final List<Fitting> fittings, final OutputStream out) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(toDNAContent(fittings));
        writer.flush();
    }

    public static String toDNAContent(final List<Fitting> fittings) {
        final StringBuilder b = new StringBuilder();
        for (Fitting f: fittings) {
            b.append(toDNAContent(f));
            b.append("\n\n");
        }
        return b.toString();
    }

    public static String toDNAContent(final Fitting f) {
        final StringBuilder b = new StringBuilder();
        b.append("[");
        b.append(f.getTypeName());
        b.append(",");
        b.append(f.getName());
        b.append("]\n");

        b.append(toClipboard(f, Attribute.FIT_LOW_SLOTS));
        b.append(toClipboard(f, Attribute.FIT_MEDIUM_SLOTS));
        b.append(toClipboard(f, Attribute.FIT_HIGH_SLOTS));
        b.append(toClipboard(f, Attribute.FIT_RIGS_SLOTS));
       // b.append(toClipboard(f, Attribute.FIT_SUBSYSTEM_SLOTS));

     //   b.append(toClipboard(f.getDrones()));
     //   b.append(toClipboard(f.getCargo()));

        return b.toString();
    }

    private static String toClipboard(final Fitting f, final int slotAttributeId) {
        final Map<Integer, String> modules = f.getSlot(slotAttributeId);
        if (modules.isEmpty()) {
            return "";
        }
        final StringBuilder b = new StringBuilder();
        for (String m: modules.values()) {
            b.append(m);
            b.append("\n");
        }
        b.append("\n\n");
        return b.toString();
    }

    private static String toClipboard(Map<String, Integer> items) {
        if (items.isEmpty()) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        for (Map.Entry<String, Integer> e: items.entrySet()) {
            b.append(e.getKey());
            b.append(" x");
            b.append(e.getValue());
        }
        b.append("\n\n");
        return b.toString();
    }

    /*DNA -> SHIP ':' HIGHS ':' MEDS ':' LOWS ':' RIGS ':' CHARGES
SHIP -> SHIP_TYPE_ID ( ':' SUBSYSTEM_ID ':' SUBSYSTEM_ID ':' SUBSYSTEM_ID ':' SUBSYSTEM_ID ':' SUBSYSTEM_ID )
HIGHS -> EMPTY | MODULE ( ':' MODULE )
MEDS -> EMPTY | MODULE ( ':' MODULE )
LOWS -> EMPTY | MODULE ( ':' MODULE )
RIGS -> EMPTY | MODULE ( ':' MODULE )
CHARGES -> EMPTY | CHARGE ( ':' CHARGE )
MODULE -> MODULE_ID ';' QUANTITY
CHARGE -> CHARGE_ID ';' QUANTITY
SHIP_TYPE_ID -> the typeID of a ship
SUBSYSTEM_ID -> the typeID of the fitted subsystems
MODULE_ID -> the typeID of the fitted module
CHARGE_ID -> the typeID of a charge or a drone
QUANTITY -> an integer quantity of the type.*/

    private static List<Fitting> fromDNAContent(final BufferedReader reader) throws IOException {
        final List<Fitting> fittings = new LinkedList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (StringUtils.isBlank(line)) {
                continue;
            }
            if (line.startsWith("[") && line.endsWith("]")) {
                final Fitting fitting = fromDNAContent(line, reader);
                if (null != fitting) {
                    fittings.add(fitting);
                }
            }
        }
        return fittings;
    }

    private static Fitting fromDNAContent(final String shipTitle, final BufferedReader reader) throws IOException {
        final String[] typeAndName = StringUtils.split(StringUtils.substringBetween(shipTitle, "[", "]"), ",");
        if (ArrayUtils.isEmpty(typeAndName) || typeAndName.length != 2) {
            return null;
        }

        final Fitting fitting = new Fitting();
        fitting.setTypeName(typeAndName[0].trim());
        fitting.setName(typeAndName[1].trim());
        fitting.setDescription(shipTitle.trim());

        fill(fitting, Attribute.FIT_LOW_SLOTS, readDNASection(reader));
        fill(fitting, Attribute.FIT_MEDIUM_SLOTS, readDNASection(reader));
        fill(fitting, Attribute.FIT_HIGH_SLOTS, readDNASection(reader));
        fill(fitting, Attribute.FIT_RIGS_SLOTS, readDNASection(reader));
        //sections.put(Attribute.FIT_SUBSYSTEM_SLOTS, readDNASection(reader));
        // fill(fitting, Attribute.FIT_DRONES, readDNASection(reader));
        //fill(fitting, Attribute.FIT_CARGO, readDNASection(reader));

        return fitting;
    }

    private static void fill(final Fitting fitting, final int slotAttrID, final List<String> section) {
        for (int i = 0; i < section.size(); i++) {
            final String item = section.get(i);
            if (!StringUtils.startsWithIgnoreCase(item, "[Empty")) {
                fitting.setItem(item, Fitting.flagOf(slotAttrID, i));
            }
        }
    }

    private static List<String> readDNASection(final BufferedReader reader) throws IOException {
        final List<String> section = new LinkedList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (StringUtils.isBlank(line)) {
                return section;
            }
            section.add(line);
        }
        return section;
    }

}
