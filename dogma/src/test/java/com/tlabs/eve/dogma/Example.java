package com.tlabs.eve.dogma;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.extra.format.FittingFormat;
import com.tlabs.eve.dogma.ormlite.OrmLiteFittingProvider;

public class Example {

    @Test
    public void example() throws Exception {
        final List<Fitting> fittings = FittingFormat.fromJSON(Example.class.getResourceAsStream("/fittings-test.json"));
        Assert.assertNotNull("Null fittings", fittings);
        Assert.assertFalse("Empty fittings", fittings.isEmpty());

        final Fitter.Builder builder = new Fitter.Builder(new OrmLiteFittingProvider());

        for (Fitting f: fittings) {
            final Fitter fitter = builder.build(f);
            Assert.assertNotNull(fitter);
            Assert.assertFalse(StringUtils.isBlank(fitter.getTypeName()));
        }
    }

}
