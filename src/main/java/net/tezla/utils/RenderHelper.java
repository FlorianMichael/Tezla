package net.tezla.utils;

import net.tezla.utils.hooks.MinecraftClientHook;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class RenderHelper implements MinecraftClientHook {

    public static final MatrixStack GLOBAL_MATRIX = new MatrixStack();

    public static int drawString(String text, int x, int y, int color) {
        return MC.textRenderer.drawWithShadow(GLOBAL_MATRIX, new LiteralText(text), x, y, color);
    }

    public static int drawCenteredString(String text, int centerX, int y, int color) {
        return MC.textRenderer.drawWithShadow(GLOBAL_MATRIX, text, (float)(centerX - MC.textRenderer.getWidth(text) /
                2), (float)y, color);
    }
}
