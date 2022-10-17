package pl.edu.uwm.JPEG;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static pl.edu.uwm.Constants.*;
import static pl.edu.uwm.Constants.V;

public class JPEG {
    private Color[][] sourceImage;
    private double[][][] yuvArray;
    private Subsampling.Type subsamplingType;
    private double[][] layerY, layerU, layerV;
    private int quality;

    public void readImage(String filePath) throws Exception {
        ImageReader imageReader = new ImageReader(filePath);
        sourceImage = imageReader.getImageArray();
    }

    public void convertToYUV() throws Exception {
        YUVConverter converter = new YUVConverter(sourceImage);
        yuvArray = converter.getYUVArray();
//        writeChannelsImages("layer");
    }

    public void setSubsamplingType(Subsampling.Type subsamplingType) {
        this.subsamplingType = subsamplingType;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void subsampling() {
        Subsampling subsampling = new Subsampling(yuvArray, subsamplingType);
        layerY = subsampling.getLayerY();
        layerU = subsampling.getLayerU();
        layerV = subsampling.getLayerV();
    }

    public void dct() throws Exception {
        QuantizationTable quantizationLum = new QuantizationTable(quantizationMatrixLuminance, quality);
        QuantizationTable quantizationChrom = new QuantizationTable(quantizationMatrixChrominance, quality);

        layerY = new DCT(layerY, quantizationLum.getResult()).getResult();
        layerU = new DCT(layerU, quantizationChrom.getResult()).getResult();
        layerV = new DCT(layerV, quantizationChrom.getResult()).getResult();

        writeImage("dctY", layerY);
        writeImage("dctU", layerU);
        writeImage("dctV", layerV);

    }

    public void encode() throws Exception {

    }

    private void writeChannelsImages(String filePath) throws IOException {
        int height = yuvArray.length;
        int width = yuvArray[0].length;

        BufferedImage biY = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage biCb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage biCr = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {

                int r = (int) (yuvArray[y][x][Y]);
                int g = (int) (yuvArray[y][x][Y]);
                int b = (int) (yuvArray[y][x][Y]);

                biY.setRGB(x, y, (r << 16) | (g << 8) | b);

                r = 0;
                g = (int) (255 - yuvArray[y][x][U]);
                b = (int) (yuvArray[y][x][U]);

                biCb.setRGB(x, y, (r << 16) | (g << 8) | b);

                r = (int) (yuvArray[y][x][V]);
                g = (int) (255 - yuvArray[y][x][V]);
                b = 0;

                biCr.setRGB(x, y, (r << 16) | (g << 8) | b);
            }

        ImageIO.write(biY, "png", new File(filePath + "Y.png"));
        ImageIO.write(biCb, "png", new File(filePath + "U.png"));
        ImageIO.write(biCr, "png", new File(filePath + "V.png"));
    }

    private void writeImage(String filePath, double[][] array) throws Exception {
        int height = array.length;
        int width = array[0].length;
        BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int r = (int) (array[y][x]);
                int g = (int) (array[y][x]);
                int b = (int) (array[y][x]);
                im.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        ImageIO.write(im, "png", new File(filePath + ".png"));

    }

}
