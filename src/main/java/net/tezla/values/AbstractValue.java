package net.tezla.values;

import net.tezla.Tezla;

public abstract class AbstractValue<T> {

    protected T value;
    private IValueChangeable parent;
    private String name;
    private T defaultValue;

    public AbstractValue(IValueChangeable parent, String name, T defaultValue) {
        this.parent = parent;
        this.name = name;
        setValue(defaultValue);
        setDefaultValue(defaultValue);

        Tezla.getInstance().getValueList().add(this);
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        getParent().onValueChange(this, value);
    }

    public IValueChangeable getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }
}
