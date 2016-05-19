package com.tlabs.eve.dogma.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tlabs.eve.dogma.engine.FittingModel;

public class Operator {
    private interface Operation {
        double apply(final double arg1, final double arg2);
    }
    /**
     * http://go-dl1.eve-files.com/media/corp/thegrandpumba/StackingPenaltyGuide.pdf
     */
    private static final double[] penalisationConstants = {
            1.000000000000,
            0.869119980800,
            0.570583143511,
            0.282955154023,
            0.105992649743,
            0.029991166533,
            0.006410183118,
            0.001034920483,
            0.000126212683,
            0.000011626754,
            0.000000809046
    };

    private static final Map<String, Operation> operations;
    static {
        operations = new HashMap<>();
        operations.put("PreAssign", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1;
            }
        });
        operations.put("PreAssignment", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1;
            }
        });
        operations.put("PostAssignment", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1;
            }
        });

        operations.put("PreMul", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1 * arg2;
            }
        });
        operations.put("PostMul", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1 * arg2;
            }
        });

        operations.put("PreDiv", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1 / arg2;
            }
        });
        operations.put("PostDiv", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1 / arg2;
            }
        });

        operations.put("ModAdd", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1 + arg2;
            }
        });
        operations.put("ModSub", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return arg1 - arg2;
            }
        });

        operations.put("PostPercent", new Operation() {
            @Override
            public double apply(double arg1, double arg2) {
                return  arg1 * ((arg2 / 100.0) + 1);
            }
        });
    }

    private static final List<String> nonPenalisedOperations = Arrays.asList("PreAssign", "ModAdd", "ModSub", "PreAssignment", "PostAssignment");

    private static final List<String> penalisedOperations = Arrays.asList("PreMul", "PreDiv", "PostMul", "PostDiv", "PostPercent");

    private String name;

    private boolean removing;

    private int affectedAttributeId;

    private boolean canPenaliseModule;

    private int modulePenaltyIndex;

    public Operator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAttributeId(int affectedAttributeId) {
        this.affectedAttributeId = affectedAttributeId;
    }

    public void setRemoving(boolean removing) {
        this.removing = removing;
    }

    // Penalties galore
    private void preApply(FittingModel module, FittingModel ship) {

      /*  if (
            affectedAttributeId > 0 && penalisableAttributeIds.contains(Long.valueOf(affectedAttributeId).intValue())) {
            if (removing) {
                ship.decrementPenalisedAttributeCount(affectedAttributeId);
            }
            else {
                ship.incrementPenalisedAttributeCount(affectedAttributeId);
            }

            modulePenaltyIndex = ship.getPenalisedAttributeCount(affectedAttributeId) - 1;
            if (modulePenaltyIndex < 0) {
                modulePenaltyIndex = 0;
            }
        }*/
    }

    // TODO: Where to include stacking penalty calculations?
    public double apply(
            FittingModel module,
            FittingModel ship,
            double arg1, double arg2) {
        if (this.affectedAttributeId == 0) {
            throw new IllegalStateException("attributeID is not set");
        }

        final Operation operation = operations.get(name);
        if (null == operation) {
            throw new UnsupportedOperationException("Unknown operator " + name);
        }

        preApply(module, ship);

        if (penalisedOperations.contains(name)) {
            // use the modulePenaltyIndex value to get the penalty constant
            double penaltyConstant =
                    (modulePenaltyIndex < penalisationConstants.length) ?
                            penalisationConstants[modulePenaltyIndex] : 0;
            arg2 = arg2 * penaltyConstant;
        }

        final double result = operation.apply(arg1, arg2);
        ship.setAttributeValue(affectedAttributeId, result);
        return result;
    }
}
