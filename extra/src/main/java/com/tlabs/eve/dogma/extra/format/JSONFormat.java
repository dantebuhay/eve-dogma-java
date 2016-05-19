package com.tlabs.eve.dogma.extra.format;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlabs.eve.dogma.Fitting;

final class JSONFormat {
    private static ObjectMapper jsonMapper = new ObjectMapper();

    public static final List<Fitting> fromContent(final InputStream in) throws IOException {
        return Transformer.fromMapper(in, jsonMapper);
    }

    public static void toJSONContent(final List<Fitting> fittings, final OutputStream out) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        jsonMapper.writeValue(writer, fittings);
        writer.flush();
    }

    public static String toJSONContent(final Fitting fitting) {
        return toJSONContent(Collections.singletonList(fitting));
    }

    public static String toJSONContent(final List<Fitting> fittings) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            toJSONContent(fittings, out);
            return out.toString();
        }
        catch (IOException e) {
            return "";
        }
        finally {
            try {
                out.close();
            }
            catch (IOException ignored){}
        }
    }

}
