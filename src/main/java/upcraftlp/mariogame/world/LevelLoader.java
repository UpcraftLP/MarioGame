package upcraftlp.mariogame.world;

import com.google.gson.JsonObject;
import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.util.ImageUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * (c)2017 UpcraftLP
 */
public class LevelLoader {

    private static JsonObject saveFile;
    private static File levelDir;
    private static List<LevelLocal> offlineLevels;

    public static void discoverLevels() {
        saveFile = new JsonObject();
        levelDir = new File(MarioGame.getGameDirectory(), "levels");
        if(!levelDir.exists()) levelDir.mkdirs(); //make sure the file exists to not cause an exception later
        File[] levels = levelDir.listFiles(File::isDirectory);
        offlineLevels = new ArrayList<>(levels.length);
        for(File levelFile : levels) {
            LevelLocal out = new LevelLocal(new JsonObject());
            File displayImage = new File(levelFile, "icon.png");
            if(ImageUtils.isImageAndNotNull(displayImage)) {
                try {
                    out.setIcon(ImageIO.read(displayImage));
                }
                catch (IOException e) {
                    e.printStackTrace(); //TODO error handling
                }
            }
            offlineLevels.add(out);
        }
        //TODO BufferedImage#getRGB() --> level design
    }

    public static List<LevelLocal> getOfflineLevels() {
        return offlineLevels;
    }
}
