package pl.edu.uwm.encoder;

import static pl.edu.uwm.Constants.BLOCK_SIZE;

public class Quantization {

    private double[][] result;
    private double[][] quantizationMatrix;

    public Quantization(double[][] layer, double[][] quantizationMatrix, int quality) {
            generateQuantizationMatrix(quantizationMatrix, quality);
    }

    private void generateQuantizationMatrix(double[][] quantizationMatrix, int quality) {

    }


}
