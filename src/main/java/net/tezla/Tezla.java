package net.tezla;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tezla {

    public static final Tezla INSTANCE = new Tezla();

    public final Logger LOGGER = LogManager.getLogger("Tezla-Mod");

    public Tezla() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public void start() {
        long time = System.currentTimeMillis();
        LOGGER.info("Tezla need " + (System.currentTimeMillis() - time) + "ms to start");
    }

    public void stop() {
        long time = System.currentTimeMillis();
        LOGGER.info("Tezla need " + (System.currentTimeMillis() - time) + "ms to stop");
    }
}
