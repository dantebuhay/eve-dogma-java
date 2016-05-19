package com.tlabs.eve.dogma.extra;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.extra.format.FittingFormat;
import com.tlabs.eve.dogma.Fitting;

public class TestJSONContent {

    @Test
    public void testFromJSONContent() throws Exception {
        final List<Fitting> fittings = FittingFormat.fromJSON(TestJSONContent.class.getResourceAsStream("/fittings-test.json"));
        Assert.assertNotNull("Null fittings", fittings);
        Assert.assertEquals(3, fittings.size());

        final Fitting raven = fittings.get(1);
        Assert.assertEquals("Raven", raven.getTypeName());
        //Assert.assertEquals(1, raven.getModules().size());

       // Assert.assertEquals("Small Shield Extender II", raven.getModules(Attribute.FIT_MEDIUM_SLOTS).get(0));
    }

}
