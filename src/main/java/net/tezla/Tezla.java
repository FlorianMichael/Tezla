package net.tezla;

import com.darkmagician6.eventapi.EventManager;
import net.tezla.commands.CommandList;
import net.tezla.config.ConfigList;
import net.tezla.modules.ModuleList;
import net.tezla.utils.hooks.MinecraftClientHook;
import net.tezla.values.ValueList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Tezla implements MinecraftClientHook {

    private static Tezla instance;

    public final Logger LOGGER = LogManager.getLogger(getClientName());
    public final File DIRECTORY = new File(MC.runDirectory, getClientName());

    private ValueList valueList;
    private ModuleList moduleList;
    private ConfigList configList;
    private CommandList commandList;

    public boolean debug = true;

    public Tezla() {
        instance = this;

        onStart();
    }

    public void onStart() {
        long startTime = System.currentTimeMillis();

        if (!DIRECTORY.exists())
            LOGGER.info("Created Directory: " + DIRECTORY.mkdir());

        valueList = new ValueList();

        moduleList = new ModuleList();
        getModuleList().init();

        configList = new ConfigList();
        getConfigList().init();

        commandList = new CommandList();
        commandList.init();

        EventManager.register(getModuleList());
        EventManager.register(getCommandList());

        Runtime.getRuntime().addShutdownHook(new Thread(this::onStop));

        if (debug)
        LOGGER.info(getClientName() + " needs " + (System.currentTimeMillis() - startTime) + "ms to start!");
    }

    public void onStop() {

    }

    public static Tezla getInstance() {
        return instance;
    }

    public ValueList getValueList() {
        return valueList;
    }

    public ModuleList getModuleList() {
        return moduleList;
    }

    public ConfigList getConfigList() {
        return configList;
    }

    public CommandList getCommandList() {
        return commandList;
    }

    public String getClientName() {
        return "Tezla";
    }
}
