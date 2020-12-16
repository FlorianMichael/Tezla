package net.tezla.config;

import net.tezla.Tezla;
import net.tezla.config.list.ModuleConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ConfigList {

    public ArrayList<AbstractConfig> configs = new ArrayList<>();

    private File file;

    public void init() {
        this.add(
                new ModuleConfig()
        );
        file = new File(Tezla.getInstance().DIRECTORY, "config.cfg");
        try {
            JsonObject obj;
            if (file.exists()) {
                obj = new JsonParser().parse(new FileReader(file)).getAsJsonObject();
            }
            else {
                obj = new JsonObject();
            }

            getConfigs().stream().forEach(cfg -> cfg
                    .loadAndSetDefaults(obj.has(cfg.getSection()) ? obj.get(cfg.getSection()) : new JsonObject(), obj));

            try (FileWriter wr = new FileWriter(file)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                wr.write(gson.toJson(obj));
                wr.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            JsonObject obj = new JsonObject();
            getConfigs().stream().forEach(cfg -> cfg
                    .save(new JsonObject(), obj));
            try (FileWriter wr = new FileWriter(file)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                wr.write(gson.toJson(obj));
                wr.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(AbstractConfig... configs) {
        for (AbstractConfig c : configs) {
            this.configs.add(c);
            if (Tezla.getInstance().debug)
                Tezla.getInstance().LOGGER.info("Loading Config: " + c.getName());
        }
    }

    public ArrayList<AbstractConfig> getConfigs() {
        return configs;
    }
}
