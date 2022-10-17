package pl.edu.uwm.JPEG;

import java.awt.*;

import static pl.edu.uwm.Constants.*;

public class YUVConverter {

    private final double[][][] yuvArray;

    public YUVConverter(Color[][] rgbArray) {
        yuvArray = convertRGBtoYUV(rgbArray);
    }

    private double[][][] convertRGBtoYUV(Color[][] rgbArray) {
        int height = rgbArray.length;
        int width = rgbArray[0].length;
        double[][][] convertedArray = new double[height][width][3];
        int R, G, B;
        for (int x = 0; x < rgbArray.length; x++) {
            for (int y = 0; y < rgbArray[1].length; y++) {
                R = rgbArray[x][y].getRed();
                G = rgbArray[x][y].getGreen();
                B = rgbArray[x][y].getBlue();
                convertedArray[x][y][Y] = 0.257 * R + 0.504 * G + 0.098 * B + 16;
                convertedArray[x][y][U] = -0.148 * R - 0.291 * G + 0.439 * B + 128;
                convertedArray[x][y][V] = 0.439 * R - 0.368 * G - 0.071 * B + 128;
            }
        }
        return convertedArray;
    }

    public double[][][] getYUVArray() {
        return yuvArray;
    }



}
