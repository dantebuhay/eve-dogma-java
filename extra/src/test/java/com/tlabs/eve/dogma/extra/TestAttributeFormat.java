package com.tlabs.eve.dogma.extra;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.tlabs.eve.dogma.Fitter;
import com.tlabs.eve.dogma.extra.format.AttributeFormat;
import com.tlabs.eve.dogma.ormlite.OrmLiteFittingProvider;

public class TestAttributeFormat {

    private static Fitter fit;

    @BeforeClass
    public static void beforeTestClass() throws Exception {
        Fitter.Builder builder = new Fitter.Builder(new OrmLiteFittingProvider());
        fit = builder.build("Retriever");

        Assert.assertNotNull(fit.fit("Strip Miner I"));
        Assert.assertNotNull(fit.fit("Strip Miner I"));
        Assert.assertNotNull(fit.fit("ML-3 Scoped Survey Scanner"));
        Assert.assertNotNull(fit.fit("Mining Laser Upgrade I"));
        Assert.assertNotNull(fit.fit("Mining Laser Upgrade II"));
        Assert.assertNotNull(fit.fit("Mining Laser Upgrade II"));
        Assert.assertNotNull(fit.fit("Medium Cargohold Optimization I"));
        Assert.assertNotNull(fit.fit("Medium Cargohold Optimization I"));
        Assert.assertNotNull(fit.fit("Medium Cargohold Optimization I"));
    }

    @Test
    public void testValueOf() {

        Object expr = AttributeFormat.jexl(fit, "fit:attr(item, 76) + 'KM'");
        System.err.println("\n" + expr);

        expr = AttributeFormat.jexl(fit, "fit:format(item, 76)");
        System.err.println("\n" + expr);
    }

    @Test
    public void testName() {

        Object expr = AttributeFormat.jexl(fit, "item.itemName");
        System.err.println("\n" + expr);


        expr = AttributeFormat.jexl(fit, "fit.name");
        System.err.println("\n" + expr);

    }

    @Test
    public void testEvaluate() {
        Object expr = AttributeFormat.evaluate(fit, "{76}");
        System.err.println("\n" + expr);

        expr = AttributeFormat.evaluate(fit, "{76} + 'Km'");
        System.err.println("\n" + expr);

        expr = AttributeFormat.evaluate(fit, "{76} + {76}");
        System.err.println("\n" + expr);

        expr = AttributeFormat.evaluate(fit, "{76} + {76} + 'Km'");
        System.err.println("\n" + expr);

        expr = AttributeFormat.evaluate(fit, "{76u}");
        System.err.println("\n" + expr);

        expr = AttributeFormat.evaluate(fit, "{76} + '/' + {76u}");
        System.err.println("\n" + expr.getClass().getSimpleName() + ": " + expr);


        expr = AttributeFormat.evaluate(fit, "{76u} | {77}");
        System.err.println("\n" + expr.getClass().getSimpleName() + ": " + expr);

        expr = AttributeFormat.evaluate(fit, "{77} | {76u}");
        System.err.println("\n" + expr.getClass().getSimpleName() + ": " + expr);


        expr = AttributeFormat.evaluate(fit, "{208}|{209}|{210}|{211}");
        System.err.println("\n" + expr.getClass().getSimpleName() + ": " + expr);
    }
}
