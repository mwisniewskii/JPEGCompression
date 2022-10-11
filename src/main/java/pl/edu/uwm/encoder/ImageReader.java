package pl.edu.uwm.encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {

    private final Color[][] imageArray;

    public ImageReader(String filePath) throws IOException {
        BufferedImage sourceImage = readFile(filePath);
        imageArray = convertToArray(sourceImage);
    }

    private BufferedImage readFile(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }

    private Color[][] convertToArray(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        int newHeight = height % 8 == 0 ? height : (1 + height / 8) * 8;
        int newWidth = width % 8 == 0 ? width : (1 + width / 8) * 8;
        Color[][] rgbImage = new Color[newHeight][newWidth];
        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                if (y >= height || x >= width) {
                    rgbImage[y][x] = new Color(0);
                } else {
                    rgbImage[y][x] = new Color(image.getRGB(x, y));
                }

            }
        }
        return rgbImage;
    }

    public Color[][] getImageArray() {
        return imageArray;
    }
}
