package upcraftlp.mariogame;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import upcraftlp.mariogame.render.ScreenRenderer;
import upcraftlp.mariogame.util.IShutdownListener;
import upcraftlp.mariogame.util.Side;
import upcraftlp.mariogame.util.Util;
import upcraftlp.mariogame.world.LevelProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * (c)2017 UpcraftLP
 */
public class MarioGame implements IShutdownListener {

    private static MarioGame instance;
    private static final Options options = new Options();
    private static final Logger log = LogManager.getLogger("Main");
    private static Side SIDE = Side.CLIENT;
    private final LevelProvider levelPovider;
    private final ScreenRenderer renderer;
    private boolean shouldShutdown = false;

    static {
        options.addOption("h", "help", false, "dispaly this usage screen");
        options.addOption("nogui", false, "start in headless mode");
        options.addOption("s", "server", false, "start as server"); //TODO: server mode for multiplayer
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
        instance = new MarioGame(); //TODO side?
        log.info("Game initialized!");
    }

    private MarioGame() {
        log.info("Starting up game...");
        this.renderer = new ScreenRenderer();
        this.levelPovider = new LevelProvider();
        this.renderer.start();
        this.levelPovider.start();
        //TODO main game init and everything
    }

    /**
     * cannot be null after init, so it's safe to assume nonnull
     */
    @Nonnull
    public static MarioGame getGame() {
        return instance;
    }

    public LevelProvider getLevelPovider() {
        return this.levelPovider;
    }

    public ScreenRenderer getRenderEngine() {
        return this.renderer;
    }

    public static void exit(int exitCode) {
        exit(exitCode, null);
    }

    public static void exit(int exitCode, @Nullable Throwable cause) {
        if(exitCode == 0) {
            log.info("Program terminated successfully.");
        }
        else {
            log.error("Program terminated unsuccessfully!");
            log.error("Error Code: " + exitCode);
            if(cause != null) {
                if(cause.getMessage() != null && !cause.getMessage().equals("null")) {
                    log.error("additional crash info: {}", cause.getMessage());
                }
                cause.printStackTrace();
            }
        }
        System.exit(exitCode);
    }

    public static Logger getLogger() {
        return log;
    }

    public static Side getSide() {
        return SIDE;
    }

    @Override
    public synchronized void shutdown() {
        log.info("Stopping Game...");
        //TODO shutdown everything
        this.renderer.shutdown();
        this.levelPovider.shutdown();
        this.shouldShutdown = true;
        exit(0);
    }

}
