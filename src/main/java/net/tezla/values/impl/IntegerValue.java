package net.tezla.values.impl;

import net.tezla.values.AbstractValue;
import net.tezla.values.IValueChangeable;

public class IntegerValue extends AbstractValue<Integer> {

	private final Integer min;
	private final Integer max;
	private final Integer defaultValue;

	public IntegerValue(IValueChangeable parent, String name, Integer defaultValue, Integer min, Integer max) {
		super(parent, name, defaultValue);
		this.min = min;
		this.max = max;
		this.defaultValue = defaultValue;
	}

	@Override
	public void setValue(Integer value) {
		super.setValue(value);
	}

	public Integer getMin() {
		return min;
	}

	public Integer getMax() {
		return max;
	}

	public Integer getDefaultValue() {
		return defaultValue;
	}

}
