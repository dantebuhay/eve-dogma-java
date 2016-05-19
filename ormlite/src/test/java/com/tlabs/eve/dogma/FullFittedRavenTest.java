package com.tlabs.eve.dogma;

import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.engine.FittingModel;


public class FullFittedRavenTest extends FittingEngineTest {

    private static final String[] FIT = new String[] {
            "Ballistic Control System II",
            "Ballistic Control System II",
            "Ballistic Control System II",
            "Ballistic Control System II",
            "Power Diagnostic System II",
            "Adaptive Invulnerability Field II",
            "Adaptive Invulnerability Field II",
            "Large Shield Extender II",
            "Large Shield Extender II",
            "'Arbalest' Cruise Launcher I",
            "'Arbalest' Cruise Launcher I",
            "'Arbalest' Cruise Launcher I",
            "'Arbalest' Cruise Launcher I",
            "'Arbalest' Cruise Launcher I",
            "'Arbalest' Cruise Launcher I",
            "Drone Link Augmentor I",
            "Large Semiconductor Memory Cell I",
            "Large Semiconductor Memory Cell I",
            "Large Semiconductor Memory Cell I"
    };

    @Test
    public void testFitting() {
        final FittingModel.Ship ship = new FittingModel.Ship(provider.findItem("Raven"));
        fit(ship, FIT);

        //FIXME
        //Assert.assertEquals(12810d, ship.getAttribute("shieldCapacity").getCurrentValue(), 0.1d);

        Assert.assertEquals(716d, ship.getAttribute("cpuLoad").getCurrentValue(), 0.1d);
        Assert.assertEquals(9027d, ship.getAttribute("powerLoad").getCurrentValue(), 0.1d);

        Assert.assertEquals(5800d, ship.getAttribute("armorHP").getCurrentValue(), 0.1d);
        Assert.assertEquals(8783.1d, ship.getAttribute("capacitorCapacity").getCurrentValue(), 0.1d);
    }

    @Test
    public void testAddAmmo() {
        final FittingModel.Ship ship = new FittingModel.Ship(provider.findItem("Raven"));
        fit(ship, FIT);

    }
}
