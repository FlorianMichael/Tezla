package net.tezla.values.impl;

import net.tezla.values.AbstractValue;
import net.tezla.values.IValueChangeable;

import java.util.Locale;

public class DoubleValue extends AbstractValue<Double> {

    private final Double min;
    private final Double max;
    private final Double defaultValue;

    public DoubleValue(IValueChangeable parent, String name, Double defaultValue, Double min, Double max) {
        super(parent, name, defaultValue);
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
    }

    @Override
    public void setValue(Double value) {
        value = Double.valueOf(String.format(Locale.ENGLISH, "%.2f", value.doubleValue()));
        if (min == null || max == null) {
            super.setValue(value);
            return;
        }
        double d = value.doubleValue();
        if (d >= min.doubleValue() && d <= max.doubleValue()) {
            super.setValue(value);
        }
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getDefaultValue() {
        return defaultValue;
    }

}
