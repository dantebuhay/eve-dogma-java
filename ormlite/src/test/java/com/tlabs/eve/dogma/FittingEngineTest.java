package com.tlabs.eve.dogma;

import org.junit.Assert;
import org.junit.BeforeClass;
import com.tlabs.eve.dogma.engine.FittingEngine;
import com.tlabs.eve.dogma.engine.FittingModel;
import com.tlabs.eve.dogma.model.Item;
import com.tlabs.eve.dogma.ormlite.OrmLiteFittingProvider;

public class FittingEngineTest {

    protected static FittingEngine engine;
    protected static FittingProvider provider;

    @BeforeClass
    public static void beforeFittingEngineTest() throws Exception {
        engine = new FittingEngine();
        provider = new OrmLiteFittingProvider();
    }


    protected final void fit(final FittingModel.Ship ship, final String... modules) {
        for (String m: modules) {
            fit(ship, provider.findItem(m));
        }
    }

    protected final void fit(final FittingModel.Ship ship, final Item module) {
        Assert.assertNotNull("Null ship", ship);
        Assert.assertNotNull("Null module", module);

        engine.fit(ship, module);
    }

    protected final void unfit(final FittingModel.Ship ship, final String... modules) {
        for (String m: modules) {
            unfit(ship, provider.findItem(m));
        }
    }

    protected final void unfit(final FittingModel.Ship ship, final Item module) {
        Assert.assertNotNull("Null ship", ship);
        Assert.assertNotNull("Null module", module);

        engine.unfit(ship, module);
    }
}
