package upcraftlp.mariogame.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.util.StoppableThread;
import upcraftlp.mariogame.util.Util;

/**
 * (c)2017 UpcraftLP
 */
public class ScreenRenderer extends StoppableThread {

    private final Logger log = LogManager.getLogger("Render");
    private Screen mainwindow; //TODO needed?

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        Util.setThreadname("Render");
        log.info("initializing render engine...");
        this.mainwindow = new Screen(MarioGame.getScreenResolution());
        super.run();
    }

    @Override
    protected void exit() {
        log.info("stopping render engine..");
        //TODO shutdown renderer
        super.exit();
    }

    @Override
    protected void runLoop() {
        //TODO tick render engine
    }

}
