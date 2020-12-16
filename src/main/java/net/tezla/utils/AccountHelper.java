package net.tezla.utils;

import net.tezla.injection.interfaces.IMixinMinecraftClient;
import net.tezla.utils.hooks.MinecraftClientHook;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.util.Session;

import java.net.Proxy;

public class AccountHelper implements MinecraftClientHook {

    public static void login(String name) {
        ((IMixinMinecraftClient) MC).setSession(new Session(name, "-", "-",
                "legacy"));
    }

    public static void login(String email, String password) {
        try {
            YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
            YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service
                    .createUserAuthentication(Agent.MINECRAFT);

            auth.setUsername(email);
            auth.setPassword(password);
            auth.logIn();

            ((IMixinMinecraftClient) MC).setSession(new Session(auth.getSelectedProfile().
                    getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(),
                    "mojang"));
        } catch (AuthenticationException exception) {
            exception.printStackTrace();
        }
    }
}
