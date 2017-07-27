package upcraftlp.mariogame;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.gui.Button;
import upcraftlp.mariogame.gui.GuiScreen;
import upcraftlp.mariogame.render.Screen;
import upcraftlp.mariogame.render.ScreenRenderer;
import upcraftlp.mariogame.render.tickable.TickableSlide;
import upcraftlp.mariogame.util.Side;
import upcraftlp.mariogame.util.SysUtils;
import upcraftlp.mariogame.world.LevelProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.event.*;
import java.io.File;

/**
 * (c)2017 UpcraftLP
 */
public class MarioGame implements ActionListener, MouseListener, KeyListener {

    private static MarioGame instance;
    private static final Options options = new Options();

    private final Logger log = LogManager.getLogger("Main");
    private static Side SIDE = Side.CLIENT;
    private final LevelProvider levelProvider;
    private final ScreenRenderer renderer;
    private volatile boolean shouldStop = false;
    public GuiScreen currentScreen;
    private static File gameDirectory;

    static {
        options.addOption("h", "help", false, "dispaly this usage screen");
        options.addOption("nogui", false, "start in headless mode (ignored on client side)");
        options.addOption("s", "server", false, "start as server"); //TODO: server mode for multiplayer
        options.addOption(Option.builder("d").longOpt("dir").hasArg().desc("the save directory").required().build());
    }


    public static void main(String[] args) {
        //TODO: bootstrap
        SysUtils.setThreadname("Main");
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
            gameDirectory = new File(cmd.getOptionValue("d"));
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
            //keep track of everything
            //TODO networking?

        }
        this.stop();
    }

    /**
     * @return the main game directory
     */
    public static File getGameDirectory() {
        return gameDirectory;
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
            log.error("Error Code: " + exitCode); //TODO exitcodes for several exceptions
            if(cause != null) {
                if(cause.getMessage() != null && !cause.getMessage().equals("null")) {
                    log.error("additional crash info: {}", cause.getMessage());
                }
            }
        }
        System.exit(exitCode);
    }

    public void displayGuiScreen(GuiScreen newScreen) {
        if(this.currentScreen != null) this.currentScreen.onClose();
        this.currentScreen = newScreen;
        Screen mainWindow = this.renderer.getMainWindow();
        if(this.currentScreen != null) this.currentScreen.setDimensions(mainWindow.getWidth(), mainWindow.getHeight());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.currentScreen != null) {
            if(e.getSource() instanceof Button) {
                this.currentScreen.actionPerformed((Button) e.getSource());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(inGameHasFocus()) {
            if(this.currentScreen != null) this.currentScreen.keyTyped(e.getKeyChar(), e.getKeyCode());
            else this.renderer.keyTyped(e.getKeyChar(), e.getKeyCode());
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(inGameHasFocus()) {
            if(this.currentScreen != null) this.currentScreen.keyPressed(e.getKeyChar(), e.getKeyCode());
            else this.renderer.keyPressed(e.getKeyChar(), e.getKeyCode());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(inGameHasFocus()) {
            if(this.currentScreen != null) this.currentScreen.keyReleased(e.getKeyChar(), e.getKeyCode());
            else this.renderer.keyReleased(e.getKeyChar(), e.getKeyCode());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(inGameHasFocus()) {
            if(this.currentScreen != null) this.currentScreen.mouseClicked(e.getX(), e.getY(), e.getButton());
            else this.renderer.mouseClicked(e.getX(), e.getY(), e.getButton());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { //TODO window dragging
        if(inGameHasFocus()) {
            if(this.currentScreen != null) this.currentScreen.mousePressed(e.getX(), e.getY(), e.getButton());
            else this.renderer.mousePressed(e.getX(), e.getY(), e.getButton());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(inGameHasFocus()) {
            if(this.currentScreen != null) this.currentScreen.mouseReleased(e.getX(), e.getY(), e.getButton());
            else this.renderer.mouseReleased(e.getX(), e.getY(), e.getButton());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(inGameHasFocus()) {
            //TODO
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(inGameHasFocus()) {
            //TODO
        }
    }

    public Logger getLogger() {
        return this.log;
    }

    public static void slide(String message) {
        getGame().getRenderEngine().addTickListener(new TickableSlide("Error:\nNot implemented yet!"));

        //TODO this is a popup!
        //JOptionPane.showMessageDialog(MarioGame.getGame().getRenderEngine().getMainWindow(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean inGameHasFocus() {
        return getGame().getRenderEngine().getMainWindow().hasFocus();
    }

}
