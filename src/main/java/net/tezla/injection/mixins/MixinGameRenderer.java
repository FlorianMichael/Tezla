package net.tezla.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.tezla.events.Render3DEvent;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Inject(method = "renderWorld", at = @At(value = "FIELD", target =
            "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    public void injectRenderWorld(float tickDelta, long limitTime, MatrixStack matrix, CallbackInfo ci) {
        EventManager.call(new Render3DEvent(tickDelta, matrix));
    }
}
