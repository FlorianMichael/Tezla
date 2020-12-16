package net.tezla.values;

import net.tezla.Tezla;
import net.tezla.modules.Module;

import java.util.ArrayList;
import java.util.List;

public class ValueList {

    private ArrayList<AbstractValue<?>> values = new ArrayList<AbstractValue<?>>();

    public void add(AbstractValue<?>... values) {
        for (AbstractValue<?> v : values) {
            this.values.add(v);
            if (Tezla.getInstance().debug)
                Tezla.getInstance().LOGGER.info("Loading Value: " + v.getName());
        }
    }
    public ArrayList<AbstractValue<?>> getValuesByMod(Module mod) {
        ArrayList<AbstractValue<?>> out = new ArrayList<AbstractValue<?>>();
        for (AbstractValue<?> s : values) {
            if (s.getParent().equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    public AbstractValue<?> getValueByName(String name, List<AbstractValue<?>> valueList) {
        for (AbstractValue<?> set : valueList) {
            if (set.getName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        return null;
    }

    public AbstractValue<?> getValueByName(String name) {
        for (AbstractValue<?> set : values) {
            if (set.getName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        return null;
    }

}
