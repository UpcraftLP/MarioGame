package upcraftlp.mariogame.util;

import org.apache.logging.log4j.ThreadContext;
import upcraftlp.mariogame.MarioGame;

/**
 * (c)2017 UpcraftLP
 */
public class SysUtils {

    public static void setThreadname(String name) {
        String side = MarioGame.getSide().name();
        ThreadContext.put("name", name);
        ThreadContext.put("side", side);
        Thread.currentThread().setName(side + " THREAD/" + name);
    }

    /**
     * Get the time in milliseconds
     *
     * @return The system time in milliseconds
     */
    public static long getTime() {
        return System.nanoTime() / 1000000;
    }

}
