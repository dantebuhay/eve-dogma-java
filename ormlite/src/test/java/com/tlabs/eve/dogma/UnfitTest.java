package com.tlabs.eve.dogma;

import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.engine.FittingModel;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Item;


public class UnfitTest extends FittingEngineTest {

    @Test
    public void testFitUnfit() {
        final FittingModel.Ship ship = new FittingModel.Ship(provider.findItem("Raven"));
        final Attribute shieldCap = ship.getAttribute("shieldCapacity");
        Assert.assertEquals(7000d, shieldCap.getCurrentValue(), 0.1d);

        final Item module = provider.findItem("Small Shield Extender II");

        fit(ship, module);
        Assert.assertEquals(7520d, shieldCap.getCurrentValue(), 0.1d);

        unfit(ship, module);
        Assert.assertEquals(7000d, shieldCap.getCurrentValue(), 0.1d);
    }

}
