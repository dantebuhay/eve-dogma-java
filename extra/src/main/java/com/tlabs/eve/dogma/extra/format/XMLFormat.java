package com.tlabs.eve.dogma.extra.format;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.Fitting;

final class XMLFormat {
    private static final String XSLT = "/fittings-json.xsl";

    private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();

    public static final List<Fitting> fromContent(final InputStream in) throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        toJSON(in, bos);
        return JSONFormat.fromContent(new ByteArrayInputStream(bos.toByteArray()));
    }

    public static void toXContent(final List<Fitting> fittings, final OutputStream out) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(toXContent(fittings));
        writer.flush();
    }

    public static String toXContent(final Fitting fitting) {
        return toXContent(Collections.singletonList(fitting));
    }

    public static String toXContent(final List<Fitting> fittings) {
        final StringBuilder b = new StringBuilder();
        b.append("<?xml version=\"1.0\"?>\n\n");
        b.append("<fittings>\n");
        for (Fitting f: fittings) {
            b.append(toXContentImpl(f));
        }
        b.append("\n</fittings>\n");
        return b.toString();
    }

    private static String toXContentImpl(final Fitting f) {
        final StringBuilder b = new StringBuilder();
        b.append(String.format("\t<fitting name=\"%s\">\n", f.getName()));
        b.append(String.format("\t\t<description value=\"%s\"/>\n", f.getDescription()));
        b.append(String.format("\t\t<shipType value=\"%s\"/>\n", f.getTypeName()));

        for (Integer slot: Fitting.SLOTS.keySet()) {
            final String slotName = slotXFormat((long)slot);
            final Map<Integer, String> slotContent = f.getSlot(slot);

            int slotIndex = 0;
            for (String module: slotContent.values()) {
                b.append(String.format(
                        "\t\t<hardware slot=\"%1$s %2$s\" type=\"%3$s\"/>\n",
                        slotName, slotIndex, module));
                slotIndex = slotIndex + 1;
            }
        }
        b.append("\n\t</fitting>");
        return b.toString();
    }

    private static String slotXFormat(final long slotId) {
        switch ((int)slotId) {
            case Attribute.FIT_HIGH_SLOTS:
                return "hi slot";
            case Attribute.FIT_MEDIUM_SLOTS:
                return "med slot";
            case Attribute.FIT_LOW_SLOTS:
                return "low slot";
            case Attribute.FIT_RIGS_SLOTS:
                return "rig slot";
            case Attribute.FIT_SUBSYSTEM_SLOTS:
                return "subsystem slot";
            default:
                return "cargo";
        }
    }

    private static void toJSON(final InputStream xml, final OutputStream out) throws IOException {
        try {
            final StreamSource stylesource = new StreamSource(XMLFormat.class.getResourceAsStream(XSLT));
            final javax.xml.transform.Transformer transformer = transformerFactory.newTransformer(stylesource);

            final StreamSource xmlSource = new StreamSource(xml);
            final StreamResult result = new StreamResult(out);
            transformer.transform(xmlSource, result);
            out.flush();
        }
        catch (TransformerException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
}
