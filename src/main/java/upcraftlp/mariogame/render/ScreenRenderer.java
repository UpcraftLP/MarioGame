package upcraftlp.mariogame.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.gui.Screen;
import upcraftlp.mariogame.util.IShutdownListener;
import upcraftlp.mariogame.util.Util;

/**
 * (c)2017 UpcraftLP
 */
public class ScreenRenderer extends Thread implements IShutdownListener {

    private static final Logger log = LogManager.getLogger("Render");
    private Screen mainwindow;

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        Util.setThreadname("Render");
        log.info("initializing render engine...");
        this.mainwindow = new Screen();
    }

    @Override
    public void shutdown() {
        log.info("stopping render engine..");
        //TODO shutdown renderer
    }
}
