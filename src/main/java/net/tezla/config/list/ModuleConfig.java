package net.tezla.config.list;

import net.tezla.Tezla;
import net.tezla.config.AbstractConfig;
import net.tezla.modules.Module;
import net.tezla.values.AbstractValue;
import net.tezla.values.impl.BooleanValue;
import net.tezla.values.impl.DoubleValue;
import net.tezla.values.impl.IntegerValue;
import net.tezla.values.impl.ModeValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ModuleConfig extends AbstractConfig {

    public ModuleConfig() {
        super("modules");
    }

    @Override
    public void save(JsonElement config, JsonObject main) {
        main.add(getSection(), new JsonArray());
        JsonArray modules = main.get(getSection()).getAsJsonArray();

        Tezla.getInstance().getModuleList().getModules().forEach(module -> {
            JsonObject moduleObj = new JsonObject();
            moduleObj.addProperty("name", module.getName());
            moduleObj.addProperty("toggled", module.isToggled());

            ArrayList<AbstractValue<?>> valueList = Tezla.getInstance().getValueList().
                    getValuesByMod(module);
            if (valueList != null) {
                JsonArray values = new JsonArray();
                valueList.forEach(value -> {
                    JsonObject valueObj = new JsonObject();
                    valueObj.addProperty("name", value.getName());
                    if (value instanceof BooleanValue) {
                        valueObj.addProperty("value", ((BooleanValue) value).getValue());
                    } else if (value instanceof ModeValue) {
                        valueObj.addProperty("value", ((ModeValue) value).getValue());
                        valueObj.addProperty("index", ((ModeValue) value).getIndex());
                    } else if (value instanceof IntegerValue) {
                        valueObj.addProperty("value", ((IntegerValue) value).getValue());
                    } else if (value instanceof DoubleValue) {
                        valueObj.addProperty("value", ((DoubleValue) value).getValue());
                    }

                    values.add(valueObj);
                });
                moduleObj.add("values", values);
            }
            modules.add(moduleObj);
        });
        main.add(getSection(), modules);
    }

    @Override
    public void loadAndSetDefaults(JsonElement config, JsonObject main) {
        if (!main.has(getSection()))
            save(config, main);
        JsonArray modules = main.get(getSection()).getAsJsonArray();
        modules.forEach(moduleElem -> {
            try {
                JsonObject moduleObj = moduleElem.getAsJsonObject();
                Module module = Tezla.getInstance().getModuleList().getModByName(moduleObj.get("name").getAsString(),
                        false);
                if (module != null) {

                    if (moduleObj.get("values") != null) {
                        JsonArray values = moduleObj.get("values").getAsJsonArray();
                        values.forEach(valueElem -> {
                            JsonObject valueObj = valueElem.getAsJsonObject();
                            ArrayList<AbstractValue<?>> valueList = Tezla.getInstance().getValueList()
                                    .getValuesByMod(module);
                            AbstractValue<?> value = Tezla.getInstance().getValueList()
                                    .getValueByName(valueObj.get("name").getAsString(), valueList);
                            if (value instanceof BooleanValue) {
                                ((BooleanValue) value).setValue(valueObj.get("value").getAsBoolean());
                            } else if (value instanceof ModeValue) {
                                ((ModeValue) value).setValue(valueObj.get("value").getAsString());
                                ((ModeValue) value).setIndex(valueObj.get("index").getAsInt());
                            } else if (value instanceof IntegerValue) {
                                ((IntegerValue) value).setValue(valueObj.get("value").getAsInt());
                            } else if (value instanceof DoubleValue) {
                                ((DoubleValue) value).setValue(valueObj.get("value").getAsDouble());
                            }
                        });
                    }

                    if (moduleObj.get("toggled").getAsBoolean())
                        module.toggle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
