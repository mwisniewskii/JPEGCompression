package pl.edu.uwm;

import pl.edu.uwm.JPEG.JPEG;
import pl.edu.uwm.JPEG.Subsampling;



public class Main {
    public static void main(String[] args) throws Exception {
        JPEG encoder = new JPEG();
        encoder.setQuality(20);
        encoder.setSubsamplingType(Subsampling.Type.TYPE_4_1_1);

        encoder.readImage("C:\\projects\\JPEGCompression\\src\\main\\resources\\cookie.jpg");
        encoder.convertToYUV();
        encoder.subsampling();
        encoder.dct();
        encoder.encode();

    }
}