package net.tezla.injection.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.tezla.Tezla;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectConstructor(RunArgs args, CallbackInfo ci) {
        Tezla.INSTANCE.start();
    }
}
