package net.tezla.modules;

import com.darkmagician6.eventapi.EventManager;
import net.tezla.Tezla;
import net.tezla.utils.hooks.MinecraftClientHook;
import net.tezla.values.AbstractValue;
import net.tezla.values.IValueChangeable;

public class Module implements MinecraftClientHook, IValueChangeable, Comparable<Module> {

    private String name;

    private String description;

    private int keyBind;

    private Type type;

    private boolean toggled;

    private boolean useEvents;

    public Module(String name, String description, int keyBind, Type type) {
        setName(name);
        setDescription(description);
        setKeyBind(keyBind);
        setType(type);
    }

    public void onToggle() {
    }

    @Override
    public void onValueChange(AbstractValue<?> key, Object value) {
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

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isToggled() {
        return toggled;
    }

    public boolean isUseEvents() {
        return useEvents;
    }

    public void setUseEvents(boolean useEvents) {
        this.useEvents = useEvents;
    }

    public void toggle() {
        toggled = !toggled;

        onToggle();

        if (isUseEvents())
            if (isToggled())
                EventManager.register(this);
            else
                EventManager.unregister(this);

        Tezla.getInstance().getConfigList().save();
    }

    @Override
    public int compareTo(Module o) {
        return MC.textRenderer.getWidth(o.getName())
                - MC.textRenderer.getWidth(getName());
    }

    public enum Type {

        RENDER("Render"), MOVEMENT("Movement"), OTHER("Other"), PLAYER("Player");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
