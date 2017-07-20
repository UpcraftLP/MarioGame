package upcraftlp.mariogame.util;

import java.io.File;

/**
 * (c)2017 UpcraftLP
 */
public class ImageUtils {

    /**
     * make sure the passed file is an image in .png format
     */
    public static boolean isImageAndNotNull(File imageFile) {
        return imageFile.exists() && imageFile.isFile() && imageFile.getName().endsWith(".png");
    }
}
