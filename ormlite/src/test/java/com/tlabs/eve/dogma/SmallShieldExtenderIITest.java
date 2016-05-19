package com.tlabs.eve.dogma;

import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.engine.FittingModel;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.model.Item;


public class SmallShieldExtenderIITest extends FittingEngineTest {

    @Test
    public void testSmallShieldExtenderII() {
        final FittingModel.Ship ship = new FittingModel.Ship(provider.findItem("Raven"));
        final Item module = provider.findItem("Small Shield Extender II");
        fit(ship, module);
        final Attribute shieldCap = ship.getAttribute("shieldCapacity");
        Assert.assertEquals(7520d, shieldCap.getCurrentValue(), 0.1d);

        final Attribute cpuLoad = ship.getAttribute(49);
        Assert.assertEquals(23d, cpuLoad.getCurrentValue(), 0.1d);

        final Attribute powerLoad = ship.getAttribute(15);
        Assert.assertEquals(3d, powerLoad.getCurrentValue(), 0.1d);
    }

    @Test
    public void testTwoSmallShieldExtenderII() {
        final FittingModel.Ship ship = new FittingModel.Ship(provider.findItem("Raven"));
        final Item module = provider.findItem("Small Shield Extender II");
        fit(ship, module);
        fit(ship, module);
        final Attribute shieldCap = ship.getAttribute("shieldCapacity");
        Assert.assertEquals(8040d, shieldCap.getCurrentValue(), 0d);

        final Attribute cpuLoad = ship.getAttribute(49);
        Assert.assertEquals(46d, cpuLoad.getCurrentValue(), 0d);

        final Attribute powerLoad = ship.getAttribute(15);
        Assert.assertEquals(6d, powerLoad.getCurrentValue(), 0d);
    }
}
