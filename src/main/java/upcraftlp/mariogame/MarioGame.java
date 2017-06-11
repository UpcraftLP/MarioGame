package upcraftlp.mariogame;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.render.ScreenRenderer;
import upcraftlp.mariogame.util.Side;
import upcraftlp.mariogame.util.Util;
import upcraftlp.mariogame.world.LevelProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

/**
 * (c)2017 UpcraftLP
 */
public class MarioGame {

    private static MarioGame instance;
    private static final Options options = new Options();

    private final Logger log = LogManager.getLogger("Main");
    private static Side SIDE = Side.CLIENT;
    private static Dimension resolution;
    private final LevelProvider levelProvider;
    private final ScreenRenderer renderer;
    private volatile boolean shouldStop = false;

    static {
        options.addOption("h", "help", false, "dispaly this usage screen");
        options.addOption("nogui", false, "start in headless mode (ignored on client side)");
        options.addOption("s", "server", false, "start as server"); //TODO: server mode for multiplayer
        options.addOption("r", "resolution", true, "set the display resolution, format: [WxH], ex: r=1920x1080");
    }


    public static void main(String[] args) {
        //TODO: bootstrap
        Util.setThreadname("Main");
        CommandLineParser parser = new DefaultParser();
        instance = new MarioGame(); //TODO side?
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("s")) {
                SIDE = Side.SERVER;
                if(cmd.hasOption("nogui")) {
                    //TODO headless mode
                }
            }
            if(cmd.hasOption("r")) {
                String res = cmd.getOptionValue("r");
                String[] display = res.split("x");
                resolution = new Dimension(Integer.parseInt(display[0]), Integer.parseInt(display[1]));
            }
            else {
                resolution = new Dimension(1920, 1080);  //TODO default??
                System.out.println("no display resolution set, defaulting to " + resolution.getWidth() + "x" + resolution.getHeight() + "!");
            }

        }
        catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
        instance.run();
    }

    private MarioGame() {
        //NO-OP
        this.renderer = new ScreenRenderer();
        this.levelProvider = new LevelProvider();
    }

    public void run() {
        log.info("Starting up game...");
        this.renderer.start();
        this.levelProvider.start();
        log.info("Game initialized!");
        //TODO main game init and everything

        while(!this.shouldStop) {

        }
        this.stop();
    }

    public static Dimension getScreenResolution() {
        return resolution;
    }

    /**
     * cannot be null after init, so it's safe to assume nonnull
     */
    @Nonnull
    public static MarioGame getGame() {
        return instance;
    }

    public LevelProvider getLevelPovider() {
        return this.levelProvider;
    }

    public ScreenRenderer getRenderEngine() {
        return this.renderer;
    }

    public void exit(int exitCode) {
        exit(exitCode, null);
    }

    public void exit(int exitCode, @Nullable Throwable cause) {
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

    public static Side getSide() {
        return SIDE;
    }

    public void shutdown() {
        this.shouldStop = true;
    }

    private void stop() {
        log.info("Stopping Game...");
        //TODO shutdown everything
        this.renderer.shutdown();
        this.levelProvider.shutdown();
        exit(0);
    }

}
