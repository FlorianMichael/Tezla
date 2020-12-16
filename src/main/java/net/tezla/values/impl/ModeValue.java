package net.tezla.values.impl;

import net.tezla.values.AbstractValue;
import net.tezla.values.IValueChangeable;

import java.util.ArrayList;

public class ModeValue extends AbstractValue<String> {

    private final int defaultIndex;
    private final ArrayList<String> options;
    public int index;

    public ModeValue(IValueChangeable parent, String name, int defaultIndex, ArrayList<String> options) {
        super(parent, name, options.get(defaultIndex));
        this.defaultIndex = defaultIndex;
        this.options = options;
        index = defaultIndex;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    @Override
    public String getValue() {
        return options.get(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

}
