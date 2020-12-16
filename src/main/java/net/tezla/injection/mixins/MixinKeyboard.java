package net.tezla.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.tezla.events.PressKeyEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class MixinKeyboard {

    @Inject(method = "onKey", at = @At("HEAD"))
    public void injectOnKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        EventManager.call(new PressKeyEvent(key, scancode, i, j));
    }
}
