package pl.edu.uwm.JPEG;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static pl.edu.uwm.Constants.zigZagMappings;

public class Encoder {
    private int[][] channelArray;

    public Encoder(int[][] channelArray) {
        this.channelArray = channelArray;
//        for (int yBlock = 0; yBlock < height; yBlock += BLOCK_SIZE)
//            for (int xBlock = 0; xBlock < width; xBlock += BLOCK_SIZE)
//                blockCalculate(yBlock, xBlock);
    }

    private byte[] runLenghtEncode(int[] array) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        for (int i = 0; i < array.length; i++) {
            int count = 1;
            while (i + 1 < array.length && array[i] == array[i + 1]){
                count++;
                i++;
            }
            result.write(count);
            result.write(array[i]);
        }
        return result.toByteArray();
    }

    private int[] zigZag(int[][] array) {
        int[] result = new int[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result[zigZagMappings[i][j]]= array[i][j];
            }
        }
        return result;
    }
}
