package net.tezla.injection.mixins;

import net.tezla.Tezla;
import net.tezla.injection.interfaces.IMixinMinecraftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient implements IMixinMinecraftClient {

    @Mutable
    @Shadow @Final private Session session;

    @Inject(method = "getWindowTitle", at = @At("RETURN"), cancellable = true)
    public void injectGetWindowTitle(CallbackInfoReturnable<String> cir) {
        if (Tezla.getInstance() == null) new Tezla();

        cir.setReturnValue(Tezla.getInstance().getClientName() + " - 1.16.4");
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
