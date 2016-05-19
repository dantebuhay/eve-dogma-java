package com.tlabs.eve.dogma;

import org.junit.Assert;
import org.junit.BeforeClass;
import com.tlabs.eve.dogma.ormlite.OrmLiteFittingProvider;

public abstract class FitterTest {

    private static Fitter.Builder builder;

    @BeforeClass
    public static void beforeTestClass() throws Exception {
        builder = new Fitter.Builder(new OrmLiteFittingProvider());
    }

    protected static Fitter newFitter(final String item) {
        return builder.build(item);
    }

    protected static Fitter newFitter(final long item) {
        return builder.build(item);
    }

    protected static Fitter newFitter(final Fitting fitting) {
        return builder.build(fitting);
    }

    protected static void assertAttributeValue(Fitter fitter, int attr, double value) {
        Assert.assertEquals(value, fitter.getAttributeValue(attr), 0.1d);
    }


    protected static void assertAttributeValue(Fitter fitter, String attr, double value) {
        Assert.assertEquals(value, fitter.getAttributeValue(attr), 0.1d);
    }
}
