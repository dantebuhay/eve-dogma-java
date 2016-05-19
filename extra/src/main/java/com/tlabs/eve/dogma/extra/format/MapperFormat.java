package com.tlabs.eve.dogma.extra.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.tlabs.eve.dogma.Fitting;

class MapperFormat {

    protected final ObjectMapper mapper;

    protected MapperFormat(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public final List<Fitting> fromContent(final InputStream in) throws IOException {
        final ObjectReader r = mapper.readerFor(XFittings.class);
        final XFittings fittings = r.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(in);
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
        return Transformer.transform(xf);
    }
}
