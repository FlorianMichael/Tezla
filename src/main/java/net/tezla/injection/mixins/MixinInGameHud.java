package net.tezla.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.tezla.events.Render2DEvent;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Inject(method = "render", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", ordinal = 0, shift = At.Shift.BEFORE))
    public void injectRender(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
        EventManager.call(new Render2DEvent(matrixStack));
    }
}
