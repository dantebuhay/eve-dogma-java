package com.tlabs.eve.dogma.extra;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.tlabs.eve.dogma.Fitting;
import com.tlabs.eve.dogma.extra.format.FittingFormat;

public class TestDNAContent {

    @Test
    public void testRaven() throws Exception {
        final List<Fitting> fittings = read("raven.dna");
        Assert.assertEquals(1, fittings.size());
    }

    @Test
    public void testGnosis() throws Exception {
        final List<Fitting> fittings = read("gnosis.dna");
        Assert.assertEquals(1, fittings.size());
    }

    @Test
    public void testTwo() throws Exception {
        final List<Fitting> fittings = read("two.dna");
        Assert.assertEquals(2, fittings.size());
    }

    private List<Fitting> read(final String file) throws IOException {
        final InputStream in = TestDNAContent.class.getResourceAsStream("/" + file);
        Assert.assertNotNull("file not found " + file, in);

        final List<Fitting> fittings = FittingFormat.fromDNA(in);
        in.close();
        return fittings;
    }
}
