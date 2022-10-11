package pl.edu.uwm.encoder;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class DCT {

    private static final int BLOCK_SIZE = 8;
    double[][] result;
    double[][] channelArray;

    public DCT(double[][] channelArray) {
        this.channelArray = channelArray;
        dct();
    }

    private void dct() {
        int height = channelArray.length;
        int width = channelArray[0].length;
        result = new double[height][width];
        for (int yBlock = 0; yBlock < height; yBlock += BLOCK_SIZE)
            for (int xBlock = 0; xBlock < width; xBlock += BLOCK_SIZE)
                blockCalculate(yBlock, xBlock);
    }

    private void blockCalculate(int yBlock, int xBlock) {
        for (int yPoint = yBlock; yPoint < yBlock + BLOCK_SIZE; yPoint++) {
            for (int xPoint = xBlock; xPoint < xBlock + BLOCK_SIZE; xPoint++) {
                calculateDCT(yBlock, xBlock, yPoint, xPoint);
            }
        }
    }

    private void calculateDCT(int yBlock, int xBlock, int yPoint, int xPoint) {
        double Cy = yPoint == 0 ? 1.0 / sqrt(2) : 1;
        double Cx = xPoint == 0 ? 1.0 / sqrt(2) : 1;
        double blockSum = sumBlockDCT(yBlock, xBlock, yPoint, xPoint);
        result[yPoint][xPoint] = (1 / sqrt(2.0 * BLOCK_SIZE)) * Cy * Cx * blockSum;
    }

    private double sumBlockDCT(int yBlock, int xBlock, int yPoint, int xPoint) {
        double sum = 0.0;
        for (int y = 0; y < BLOCK_SIZE; y++)
            for (int x = 0; x < BLOCK_SIZE; x++)
                sum += channelArray[y + yBlock][x + xBlock]
                        * cos((((2.0 * y) + 1) * (yPoint) * PI) / (2.0 * BLOCK_SIZE))
                        * cos((((2.0 * x) + 1) * (xPoint) * PI) / (2.0 * BLOCK_SIZE));
        return sum;
    }

    public double[][] getResult() {
        return result;
    }
}
