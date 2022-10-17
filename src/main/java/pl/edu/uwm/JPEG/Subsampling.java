package pl.edu.uwm.JPEG;

import static pl.edu.uwm.Constants.*;

public class Subsampling {

    private final double[][][] YUVArray;

    double[][] layerY, layerU, layerV;

    public enum Type {
        TYPE_4_2_2,
        TYPE_4_1_1,
        TYPE_4_2_0,
        TYPE_4_4_4
    }

    public Subsampling(double[][][] YUVArray, Type subsamplingType) {
        this.YUVArray = YUVArray;
        subsample(subsamplingType);
    }

    private void subsample(Type subsamplingType) {
        switch (subsamplingType) {
            case TYPE_4_1_1:
                calculate(4, 1);
                break;
            case TYPE_4_2_0:
                calculate(2, 2);
                break;
            case TYPE_4_2_2:
                calculate(1, 2);
                break;
            case TYPE_4_4_4:
                calculate();
        }
    }
    private void calculate(int offsetX, int offsetY) {
        int height = YUVArray.length;
        int width = YUVArray[0].length;
        int newHeight = (height / offsetY) % 8 == 0 ? height : (1 + (height / offsetY) / 8) * 8;
        int newWidth = (width / offsetX) % 8 == 0 ? width : (1 + (width / offsetX) / 8) * 8;
        layerY = new double[height][width];
        layerU = new double[newHeight][newWidth];
        layerV = new double[newHeight][newWidth];



        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                layerY[y][x] = YUVArray[y][x][Y];
        for (int x = 0; x < width; x += offsetX)
            for (int y = 0; y < height; y += offsetY) {
                layerU[y / offsetY][x / offsetX] = colorMean(YUVArray, x, y, U, offsetX, offsetY);
                layerV[y / offsetY][x / offsetX] = colorMean(YUVArray, x, y, V, offsetX, offsetY);
            }
    }

    private void calculate() {
        int height = YUVArray.length;
        int width = YUVArray[0].length;
        layerY = new double[height][width];
        layerU = new double[height][width];
        layerV = new double[height][width];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                layerY[y][x] = YUVArray[y][x][Y];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                layerU[y][x] = YUVArray[y][x][U];
                layerV[y][x] = YUVArray[y][x][V];
            }
    }

    private double colorMean(double[][][] array, int x, int y, int channel, int offsetX, int offsetY) {
        double sum = 0;
        for (int i = x; i < x + offsetX; i++)
            for (int j = y; j < y + offsetY; j++)
                sum += array[j][i][channel];
        return sum / (offsetX * offsetY);

    }

    public double[][] getLayerY() {
        return layerY;
    }

    public double[][] getLayerU() {
        return layerU;
    }

    public double[][] getLayerV() {
        return layerV;
    }
}
