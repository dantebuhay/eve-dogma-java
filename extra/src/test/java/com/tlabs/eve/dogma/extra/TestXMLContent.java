package com.tlabs.eve.dogma.extra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.Fitter;
import com.tlabs.eve.dogma.Fitting;
import com.tlabs.eve.dogma.extra.format.FittingFormat;
import com.tlabs.eve.dogma.model.Attribute;
import com.tlabs.eve.dogma.ormlite.OrmLiteFittingProvider;

public class TestXMLContent {

    @Test
    public void testFromXContent() throws Exception {
        final List<Fitting> fittings = FittingFormat.fromXML(TestXMLContent.class.getResourceAsStream("/fittings-test.xml"));
        Assert.assertNotNull("Null fittings", fittings);
        Assert.assertEquals(3, fittings.size());
    }

    @Test
    public void testToXContent() throws Exception {
        final List<Fitting> fittings = FittingFormat.fromXML(TestXMLContent.class.getResourceAsStream("/fittings-test.xml"));
        Assert.assertNotNull("Null fittings", fittings);
        Assert.assertEquals(3, fittings.size());

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FittingFormat.toXML(fittings, bos);
        bos.flush();

        final List<Fitting> saved = FittingFormat.fromXML(new ByteArrayInputStream(bos.toByteArray()));
        Assert.assertNotNull("Null saved fitting", saved);
        Assert.assertEquals(3, saved.size());
    }

    @Test
    public void testIbis() throws Exception {
        final List<Fitting> fittings = FittingFormat.fromXML(TestXMLContent.class.getResourceAsStream("/fittings-test.xml"));
        final Fitter.Builder fitter = new Fitter.Builder(new OrmLiteFittingProvider());

        final Fitter ibis = fitter.build(fittings.get(0));
        Assert.assertEquals("Ibis", ibis.getTypeName());
        Assert.assertEquals("Ibis - Ibis", ibis.getName());
        Assert.assertEquals("Civilian Ibis", ibis.getDescription());
        Assert.assertEquals(2, ibis.getModules(Attribute.FIT_HIGH_SLOTS).size());
        Assert.assertEquals(2, ibis.getUsedModules(Attribute.FIT_HIGH_SLOTS).size());
        Assert.assertEquals(2, ibis.getModules(Attribute.FIT_MEDIUM_SLOTS).size());
        Assert.assertEquals(2, ibis.getUsedModules(Attribute.FIT_MEDIUM_SLOTS).size());
        Assert.assertEquals(2, ibis.getModules(Attribute.FIT_LOW_SLOTS).size());
        Assert.assertEquals(2, ibis.getUsedModules(Attribute.FIT_LOW_SLOTS).size());
    }
    /* <fitting name="Ibis - Ibis">
        <description value="Civilian Ibis"/>
        <shipType value="Ibis"/>
        <hardware slot="low slot 0" type="Civilian Expanded Cargohold"/>
        <hardware slot="low slot 1" type="Civilian Expanded Cargohold"/>
        <hardware slot="med slot 0" type="1MN Civilian Afterburner"/>
        <hardware slot="med slot 1" type="Civilian EM Ward Field"/>
        <hardware slot="hi slot 0" type="Civilian Light Missile Launcher"/>
        <hardware slot="hi slot 1" type="Civilian Miner"/>
    </fitting>*/
}
