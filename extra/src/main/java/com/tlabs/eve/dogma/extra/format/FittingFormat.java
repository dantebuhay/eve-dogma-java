package com.tlabs.eve.dogma.extra.format;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import com.tlabs.eve.dogma.Fitting;

public class FittingFormat {

    public static List<Fitting> fromXML(final InputStream in) throws IOException {
        return XMLFormat.fromContent(in);
    }

    public static void toXML(final Fitting fitting, final OutputStream out) throws IOException {
        XMLFormat.toXContent(Collections.singletonList(fitting), out);
    }

    public static void toXML(final List<Fitting> fittings, final OutputStream out) throws IOException {
        XMLFormat.toXContent(fittings, out);
    }

    public static String toXML(final Fitting fitting) {
        return XMLFormat.toXContent(Collections.singletonList(fitting));
    }

    public static String toXML(final List<Fitting> fittings) {
        return XMLFormat.toXContent(fittings);
    }

    public static List<Fitting> fromJSON(final InputStream in) throws IOException {
        return JSONFormat.fromContent(in);
    }

    public static void toJSON(final Fitting fitting, final OutputStream out) throws IOException {
        JSONFormat.toJSONContent(Collections.singletonList(fitting), out);
    }

    public static void toJSON(final List<Fitting> fittings, final OutputStream out) throws IOException {
        JSONFormat.toJSONContent(fittings, out);
    }

    public static String toJSON(final Fitting fitting) throws IOException {
        return JSONFormat.toJSONContent(Collections.singletonList(fitting));
    }

    public static String toJSON(final List<Fitting> fittings) {
        return JSONFormat.toJSONContent(fittings);
    }

    public static void toDNA(final List<Fitting> fittings, final OutputStream out) throws IOException {
        ShipDNAFormat.toDNAContent(fittings, out);
    }

    public static String toDNA(final List<Fitting> fittings) {
        return ShipDNAFormat.toDNAContent(fittings);
    }

    public static String toDNA(final Fitting f) {
        return ShipDNAFormat.toDNAContent(f);
    }

    public static List<Fitting> fromDNA(final InputStream in) throws IOException {
        return ShipDNAFormat.fromDNAContent(in);
    }
}
