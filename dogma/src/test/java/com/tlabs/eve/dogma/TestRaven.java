package com.tlabs.eve.dogma;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.model.Attribute;

public class TestRaven extends FitterTest {

    @Test
    public void testRavenShitFit() throws Exception {
        final Fitter raven = newFitter("Raven");
        assertRavenShitFit(raven);

        for (Attribute attr: raven.getAttributes().values()) {
            System.err.println(ToStringBuilder.reflectionToString(attr));
        }
    }

    @Test
    public void testToFitting() throws Exception {
        final Fitter raven = newFitter("Raven");
        assertRavenShitFit(raven);

        final Fitter duplicate = newFitter(raven.toFitting());
        for (Map.Entry<Integer, Attribute> attrs: raven.getAttributes().entrySet()) {
            Assert.assertEquals(
                    attrs.getValue().getCurrentValue(),
                    duplicate.getAttribute(attrs.getKey()).getCurrentValue(),
                    0.0f);
        }
    }

    @Test
    public void testLauncherSlots() throws Exception {
        Fitter raven = newFitter("Raven");
        raven.fit("'Arbalest' Cruise Launcher I");
        raven.fit("'Arbalest' Cruise Launcher I");
        raven.fit("'Arbalest' Cruise Launcher I");
        raven.fit("'Arbalest' Cruise Launcher I");
    }

    private void assertRavenShitFit(Fitter fitter) throws Exception {
        assertAttributeValue(fitter, Attribute.CPU_CAPACITY, 750d);
        assertAttributeValue(fitter, Attribute.POWER_CAPACITY, 11000d);
        assertAttributeValue(fitter, Attribute.CAPACITOR_CAPACITY, 5500d);
        assertAttributeValue(fitter, Attribute.STRUCTURE_HP, 6400d);

        Assert.assertNotNull(fitter.fit("Damage Control II"));
        assertAttributeValue(fitter, Attribute.CPU_LOAD, 30d);
        assertAttributeValue(fitter, Attribute.POWER_LOAD, 1d);

        for (int i = 0; i < 6; i++) {
            Assert.assertNotNull(fitter.fit("'Arbalest' Cruise Launcher I"));
        }

        assertAttributeValue(fitter, Attribute.CPU_LOAD, 336d);
        assertAttributeValue(fitter, Attribute.POWER_LOAD, 8701d);

        for (int i = 0; i < 3; i++) {
            Assert.assertNotNull(fitter.fit("Ballistic Control System II"));
        }
        assertAttributeValue(fitter, Attribute.CPU_LOAD, 456d);
        assertAttributeValue(fitter, Attribute.POWER_LOAD, 8704d);

        fitter.fit("Faint Epsilon Warp Scrambler I");
        assertAttributeValue(fitter, Attribute.CPU_LOAD, 484d);
        assertAttributeValue(fitter, Attribute.POWER_LOAD, 8705d);

        fitter.fit("Large Shield Extender II");
        fitter.fit("Large Shield Extender II");
        assertAttributeValue(fitter, Attribute.CPU_LOAD, 574d);
        assertAttributeValue(fitter, Attribute.POWER_LOAD, 9025d);
        assertAttributeValue(fitter, Attribute.SHIELD_HP, 12200d);
        assertAttributeValue(fitter, Attribute.ARMOR_HP, 5800d);
        assertAttributeValue(fitter, Attribute.CAPACITOR_RECHARGE, 1150000d);
        assertAttributeValue(fitter, Attribute.SIGNATURE_RADIUS, 460d);

        fitter.fit("Faint Warp Disruptor I");
        assertAttributeValue(fitter, Attribute.CPU_LOAD, 606d);
        assertAttributeValue(fitter, Attribute.POWER_LOAD, 9026d);
    }

}
