package capstone.boardgame.GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kyle on 3/1/2016.
 */
public class LoadImageFrom {
    private static final String tag = "LoadImageFrom";
    public static BufferedImage LoadImageFrom(Class<?> classFile, String path) {
        URL url = classFile.getClassLoader().getResource(path);
        BufferedImage img = null;

        try {
            img = ImageIO.read(url);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return img;
    }
}
