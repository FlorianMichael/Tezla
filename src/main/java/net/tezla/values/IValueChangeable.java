package net.tezla.values;

public interface IValueChangeable {

    void onValueChange(AbstractValue<?> key, Object value);
    String getName();
}
