package net.tezla.injection.interfaces;

import net.minecraft.client.util.Session;

public interface IMixinMinecraftClient {

    void setSession(Session session);

}
