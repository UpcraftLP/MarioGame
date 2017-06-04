package upcraftlp.mariogame;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import upcraftlp.mariogame.util.Side;
import upcraftlp.mariogame.util.Util;

import javax.annotation.Nullable;

/**
 * (c)2017 UpcraftLP
 */
public class MarioGame {

    private static final Options options = new Options();
    private static final Logger log = LogManager.getLogger("Main");
    private static Side SIDE = Side.CLIENT;

    static {
        options.addOption("h", "help", false, "dispaly this usage screen");
        options.addOption("nogui", false, "start in headless mode");
        options.addOption("s", "server", false, "start as server"); //TODO: server mode for multiplayer


        try {
            //FIXME log file
        }
        catch (Throwable t) {
            //TODO printout
        }
    }


    public static void main(String[] args) {
        //TODO: bootstrap
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("s")) {
                SIDE = Side.SERVER;
            }
        }
        catch (Throwable t) {
            exit(1, t);
        }
        ThreadContext.put("name", "Main");
        ThreadContext.put("side", SIDE.name());
        Util.setThreadname("Main");
        log.info("Starting up game...");
        exit(0);
    }

    public static void exit(int exitCode) {
        exit(exitCode, null);
    }

    public static void exit(int exitCode, @Nullable Throwable cause) {
        if(exitCode == 0) {
            log.info("Program terminated successfully.");
        }
        else {
            log.error("Program terminated unsuccessfully");
            log.error("Error Code: " + exitCode);
            if(cause != null) {
                log.error("additional crash info: {}", cause.getMessage());
                cause.printStackTrace();
            }

        }
        System.exit(exitCode);
    }
}
