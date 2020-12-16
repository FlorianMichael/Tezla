package net.tezla.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;
import net.tezla.Tezla;
import net.tezla.events.Render2DEvent;
import net.tezla.modules.Module;
import net.tezla.utils.RenderHelper;
import net.tezla.values.impl.BooleanValue;
import net.tezla.utils.hooks.MinecraftClientHook;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class HudModule extends Module {

    public final Identifier LOGO = new Identifier("tezla:logos/tezla.png");

    public BooleanValue drawLogo = new BooleanValue(this, "draw Logo", true);
    public BooleanValue drawArrayList = new BooleanValue(this, "draw ArrayList", true);

    public HudModule() {
        super("Hud", "Ingame Graphics for the Client", GLFW.GLFW_KEY_F, Type.RENDER);
        setUseEvents(true);
    }


    @EventTarget
    public void onRender2D(Render2DEvent e) {

        if (drawLogo.getValue()) {
            MC.getTextureManager().bindTexture(LOGO);
            DrawableHelper.drawTexture(RenderHelper.GLOBAL_MATRIX, 1, 1, 80, 80, 80, 80,
                    80, 80);
        }

        if (drawArrayList.getValue()) {

            int y = 0;

            List<Module> modules = Tezla.getInstance().getModuleList().getModules();
            Collections.sort(modules);

            for (Module m : modules) {
                if (m.isToggled()) {
                    RenderHelper.drawString(m.getName(), MinecraftClientHook.MC.getWindow().getScaledWidth() -
                            MinecraftClientHook.MC.textRenderer.getWidth(m.getName()), y, -1);
                    y += 10;
                }
            }
        }
    }
}
