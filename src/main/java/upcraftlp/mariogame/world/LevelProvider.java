package upcraftlp.mariogame.world;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.util.StoppableThread;
import upcraftlp.mariogame.util.Util;

/**
 * (c)2017 UpcraftLP
 */
public class LevelProvider extends StoppableThread {
    //load, unload and save worlds

    private final Logger log = LogManager.getLogger("Level");

    @Override
    public synchronized void start() {
        super.start();
        //TODO look for levels

    }

    @Override
    public void run() {
        Util.setThreadname("Level");
        log.info("Scanning for levels...");
        super.run();
    }

    @Override
    protected void exit() {
        log.info("saving world...");
        //TODO save everthing
        super.exit();
    }

    @Override
    protected void runLoop() {
        //TODO tick game
    }

}
