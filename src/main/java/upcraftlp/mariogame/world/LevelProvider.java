package upcraftlp.mariogame.world;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.util.IShutdownListener;
import upcraftlp.mariogame.util.Util;

/**
 * (c)2017 UpcraftLP
 */
public class LevelProvider extends Thread implements IShutdownListener {
    //load, unload and save worlds

    private static final Logger log = LogManager.getLogger("Level");

    @Override
    public synchronized void start() {
        super.start();
        //TODO look for levels

    }

    @Override
    public void run() {
        Util.setThreadname("Level");
        log.info("Scanning for levels...");
        while(!this.isInterrupted()) {
        }
    }

    @Override
    public void shutdown() {
        log.info("saving world...");
        //TODO save everthing
    }
}
