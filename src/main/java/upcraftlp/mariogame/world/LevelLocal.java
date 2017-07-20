package upcraftlp.mariogame.world;

import com.google.gson.JsonObject;

import java.awt.image.BufferedImage;

/**
 * (c)2017 UpcraftLP
 */
public class LevelLocal implements ILevelReference {

    private final JsonObject saveFile;
    private BufferedImage icon;

    public LevelLocal(JsonObject saveFile) {
        this.saveFile = saveFile;
    }

     @Override
    public String getDisplayName() {
        return saveFile.get("display").getAsString();
    }

    @Override
    public String getDescription() {
        return ""; //FIXME load from file!
    }

    @Override
    public String getRealName() {
        return saveFile.get("file").getAsString(); //TODO convert to file!
    }

    public World createLevelInstance() {
        return null; //FIXME load world!
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }
}
