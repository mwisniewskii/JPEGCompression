import pl.edu.uwm.JPEG.DCT;
import pl.edu.uwm.JPEG.QuantizationTable;

import java.util.Arrays;

import static pl.edu.uwm.Constants.quantizationMatrixLuminance;

public class DCTTests {
    public static void main(String[] args) throws Exception {
        double[][] testArray = new double[][]{
                {140, 144, 147, 140, 140, 155, 179, 175},
                {144, 152, 140, 147, 140, 148, 167, 179},
                {152, 155, 136, 167, 163, 162, 152, 172},
                {168, 145, 156, 160, 152, 155, 136, 160},
                {162, 148, 156, 148, 140, 136, 147, 162},
                {147, 167, 140, 155, 155, 140, 136, 162},
                {136, 156, 123, 167, 162, 144, 140, 147},
                {148, 155, 136, 155, 152, 147, 147, 136}
        };

        double[][] expectedResult = new double[][]{
                {186, -18, 15, -9, 23, -9, -14, -19},
                {21, -34, 26, -9, -11, 11, 14, 7},
                {-10, -24, -2, 6, -18, 3, -20, -1},
                {-8, -5, 14, -15, -8, -3, -3, 8},
                {-3, 10, 8, 1, -11, 18, 18, 15},
                {4, -2, -18, 8, 8, -4, 1, -7},
                {9, 1, -3, 4, -1, -7, -1, -2},
                {0, -8, -2, 2, 1, 4, -6, 0}
        };
        QuantizationTable quantizationLum = new QuantizationTable(quantizationMatrixLuminance, 20);

        double[][] result = new DCT(testArray, quantizationLum.getResult()).getResult();
        if(!Arrays.deepEquals(result, expectedResult)) System.out.println("DCT matrix not equal");
    }

}
