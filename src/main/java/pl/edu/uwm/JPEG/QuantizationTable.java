package pl.edu.uwm.JPEG;

public class QuantizationTable {

    private int[][] result;

    public QuantizationTable(int[][] quantizationMatrix, int quality) {
        result = new int[8][8];
        generateQuantizationTable(quantizationMatrix, quality);
    }

    private void generateQuantizationTable(int[][] quantizationMatrix, int quality) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int quantizationDivisor = (quantizationMatrix[i][j] * quality + 50) / 100;
                if (quantizationDivisor <= 0) quantizationDivisor = 1;
                if (quantizationDivisor > 255) quantizationDivisor = 255;
                result[i][j] = quantizationDivisor;
            }
        }
    }

    public int[][] getResult() {
        return result;
    }
}
