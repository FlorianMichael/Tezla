package net.tezla.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.tezla.events.ChatMessageEvent;
import com.sun.istack.internal.Nullable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Screen.class)
public abstract class MixinScreen {

    @Shadow @Nullable
    protected MinecraftClient client;

    /**
     * @author Flori2007
     * @reason Add Commands
     */
    @Overwrite
    public void sendMessage(String message, boolean toHud) {
        if (toHud) {
            this.client.inGameHud.getChatHud().addToMessageHistory(message);
        }

        ChatMessageEvent event = new ChatMessageEvent(message);
        EventManager.call(event);

        if (!event.isCancelled())
            this.client.player.sendChatMessage(message);
    }
}
