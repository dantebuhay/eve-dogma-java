package com.tlabs.eve.dogma;

import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.model.Attribute;

public class TestAddModule extends FitterTest {

    @Test
    public void testSlotMaxFit() throws Exception {
        final Fitter fitter = newFitter("Ibis");

        Assert.assertNotNull(fitter.fit("Civilian Light Missile Launcher"));
        Assert.assertNotNull(fitter.fit("Civilian Light Missile Launcher"));

        Assert.assertNull(fitter.fit("Civilian Light Missile Launcher"));
    }

    @Test
    public void testValidSlotFit() throws Exception {
        final Fitter fitter = newFitter("Raven");

        fitter.fit("'Arbalest' Cruise Launcher I");
        fitter.fit("'Arbalest' Cruise Launcher I");
        fitter.fit("'Arbalest' Cruise Launcher I");
        fitter.fit("'Arbalest' Cruise Launcher I");
        fitter.fit("'Arbalest' Cruise Launcher I");
        fitter.fit("'Arbalest' Cruise Launcher I");

        fitter.fit("Faint Epsilon Warp Scrambler I");
        fitter.fit("Large Shield Extender II");
        fitter.fit("Large Shield Extender II");
        fitter.fit("Faint Warp Disruptor I");

        Assert.assertNotNull(fitter.fit("Damage Control II"));
        fitter.fit("Ballistic Control System II");
        fitter.fit("Ballistic Control System II");

        Assert.assertEquals(6, fitter.getUsedModules(Attribute.FIT_HIGH_SLOTS).size());
        Assert.assertEquals(4, fitter.getUsedModules(Attribute.FIT_MEDIUM_SLOTS).size());
        Assert.assertEquals(3, fitter.getUsedModules(Attribute.FIT_LOW_SLOTS).size());
    }
}
