package pl.edu.uwm;

import pl.edu.uwm.encoder.Encoder;
import pl.edu.uwm.encoder.Subsampling;



public class Main {
    public static void main(String[] args) throws Exception {
        Encoder encoder = new Encoder();
        encoder.readImage("C:\\projects\\JPEGCompression\\src\\main\\resources\\molly.jpg");
        encoder.convertToYUV();
        encoder.setSubsamplingType(Subsampling.Type.TYPE_4_1_1);
        encoder.subsampling();
        encoder.dct();
    }
}