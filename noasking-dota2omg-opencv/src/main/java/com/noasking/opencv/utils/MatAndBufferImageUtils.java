package com.noasking.opencv.utils;

import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Mat 和 BufferImage 转换工具
 * Created by MaJing on 2018/1/25.
 */
public class MatAndBufferImageUtils {

    /**
     * Mat 转 BufferImage
     * @param matrix
     * @return
     */
    public BufferedImage matToBufferedImage(Mat matrix) {
        int cols = matrix.cols();
        int rows = matrix.rows();
        int elemSize = (int) matrix.elemSize();
        byte[] data = new byte[cols * rows * elemSize];
        int type;
        matrix.get(0, 0, data);
        switch (matrix.channels()) {
            case 1:
                type = BufferedImage.TYPE_BYTE_GRAY;
                break;
            case 3:
                type = BufferedImage.TYPE_3BYTE_BGR;
                // bgr to rgb
                byte b;
                for (int i = 0; i < data.length; i = i + 3) {
                    b = data[i];
                    data[i] = data[i + 2];
                    data[i + 2] = b;
                }
                break;
            default:
                return null;
        }
        BufferedImage image2 = new BufferedImage(cols, rows, type);
        image2.getRaster().setDataElements(0, 0, cols, rows, data);
        return image2;
    }

    public static Mat ImgToMat(BufferedImage bfImg, int imgType, int matType) {
        BufferedImage original = bfImg;
        int itype = imgType;
        int mtype = matType;

        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }

        if (original.getType() != itype) {
            BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), itype);

            Graphics2D g = image.createGraphics();
            try {
                g.setComposite(AlphaComposite.Src);
                g.drawImage(original, 0, 0, null);
            } finally {
                g.dispose();
            }
        }

        byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
        Mat mat = Mat.eye(original.getHeight(), original.getWidth(), mtype);
        mat.put(0, 0, pixels);

        return mat;
    }



}
