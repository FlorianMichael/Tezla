package net.tezla.utils;

import net.tezla.Tezla;
import net.tezla.utils.hooks.MinecraftClientHook;
import net.minecraft.text.LiteralText;

public class ChatHelper implements MinecraftClientHook {

    public static String prefix = "§7[§a" + Tezla.getInstance().getClientName() + "§7] ";

    public static void sendMessage(String message) {
        sendMessage(message, true);
    }

    public static void sendMessage(String message, boolean withPrefix) {
        LiteralText literal = new LiteralText("");
        if (withPrefix)
            literal.append(prefix);
        literal.append(message);
        MC.inGameHud.getChatHud().addMessage(literal);
    }

}
