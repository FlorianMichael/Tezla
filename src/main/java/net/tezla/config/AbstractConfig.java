package net.tezla.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractConfig {

    private String name;

    public AbstractConfig(String name) {
        setName(name);
    }

    public String getSection() {
        return this.getName();
    }

    public abstract void loadAndSetDefaults(JsonElement config, JsonObject main);
    public abstract void save(JsonElement config, JsonObject main);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
