package upcraftlp.mariogame.world;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.util.StoppableThread;
import upcraftlp.mariogame.util.SysUtils;

/**
 * (c)2017 UpcraftLP
 */
public class LevelProvider extends StoppableThread {
    //load, unload and save worlds

    private World currentWorld;
    private final Logger log = LogManager.getLogger("Level");

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        SysUtils.setThreadname("Level");
        log.info("Scanning for levels...");
        //TODO look for levels
        LevelLoader.discoverLevels();
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

    public void loadLevel(ILevelReference levelReference) {
        if(levelReference instanceof LevelLocal) {
            LevelLocal level = (LevelLocal) levelReference;
            this.currentWorld = level.createLevelInstance();
        }
        //TODO multiplayer level?
    }

    public Logger getLogger() {
        return this.log;
    }

}
