package net.tezla.commands;

import net.tezla.utils.hooks.MinecraftClientHook;

public class Command implements MinecraftClientHook {

    private String name;

    private String description;

    public Command(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public void onCommand(String commandLine, String[] args) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
