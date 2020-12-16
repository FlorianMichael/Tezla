package net.tezla.modules;

import com.darkmagician6.eventapi.EventTarget;
import net.tezla.Tezla;
import net.tezla.events.PressKeyEvent;
import net.tezla.modules.render.HudModule;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ModuleList {

    private final List<Module> modules = new ArrayList<>();

    public void init() {
        add(
                new HudModule()
        );
    }

    public List<Module> getModules() {
        return modules;
    }

    public void add(Module... modules) {
        for (Module m : modules) {
            this.modules.add(m);
            if (Tezla.getInstance().debug)
                Tezla.getInstance().LOGGER.info("Loading Module: " + m.getName());
        }
    }

    public Module getModByName(String name, boolean ignoreCase) {
        return this.getModules().stream().filter(t -> t.getName().equals(name) || ignoreCase && t.getName()
                .equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @EventTarget
    public void onPressKey(PressKeyEvent e) {
        modules.stream().filter(m -> m.getKeyBind() == e.getKeyCode() && e.getAction() == GLFW.GLFW_PRESS).
                forEach(Module::toggle);
    }
}
