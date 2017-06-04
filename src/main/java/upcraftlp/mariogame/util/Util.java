package upcraftlp.mariogame.util;

import org.apache.logging.log4j.ThreadContext;

/**
 * (c)2017 UpcraftLP
 */
public class Util {

    public static void setThreadname(String name) {
        Thread.currentThread().setName(ThreadContext.get("side") + " THREAD/" + ThreadContext.get("name"));
    }
}
