package net.tezla.values.impl;

import net.tezla.values.AbstractValue;
import net.tezla.values.IValueChangeable;

public class BooleanValue extends AbstractValue<Boolean> {

	public BooleanValue(IValueChangeable parent, String name, Boolean defaultValue) {
		super(parent, name, defaultValue);
	}

	@Override
	public void setValue(Boolean value) {
		super.setValue(value);
	}

}
