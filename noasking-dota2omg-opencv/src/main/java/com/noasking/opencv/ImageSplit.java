package com.noasking.opencv;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Created by MaJing on 2018/1/11.
 */
public class ImageSplit {

    private static final int FULL_WIDTH = 1920;
    private static final int FULL_HEIGHT = 1080;

    private static final int SKILL_SIZE = 60;

    private static final int[][] SPRING_ARRAY = {{130, 200}, {300, 400}};

    public static void split(Mat image) {
        // 获取原始图片信息
        int sourceHeight = image.rows();
        int sourceWidth = image.cols();
        System.out.println(sourceHeight);
        // 大招技能拆分
        int[][][] points = ImgPointDetail.heroPoint;
        for (int i = 0; i < points.length; i++) {
            int[][] inner = points[i];
            for (int j = 0; j < inner.length; j++) {
                System.out.println(inner[j][0]);
                int ceil_x = sourceWidth * inner[j][0] / ImgPointDetail.FULL_WIDTH;
                int ceil_y = sourceHeight * inner[j][1] / ImgPointDetail.FULL_HEIGHT;
                System.out.println(ceil_x+"-"+ceil_y);
                Rect rect = new Rect(ceil_x, ceil_y, ImgPointDetail.heroWidth, ImgPointDetail.heroHeight);
                Mat roi_img = new Mat(image, rect);
                Mat tmp_img = new Mat();
                roi_img.copyTo(tmp_img);
                Imgcodecs.imwrite("D:\\var\\imgsplit\\hero" + i + "_" + j + ".jpg", tmp_img);
            }
        }
        points = ImgPointDetail.powerAbilityPoint;
        for (int i = 0; i < points.length; i++) {
            int[][] inner = points[i];
            for (int j = 0; j < inner.length; j++) {
                System.out.println(inner[j][0]);
                int ceil_x = sourceWidth * inner[j][0] / ImgPointDetail.FULL_WIDTH;
                int ceil_y = sourceHeight * inner[j][1] / ImgPointDetail.FULL_HEIGHT;
                System.out.println(ceil_x+"-"+ceil_y);
                Rect rect = new Rect(ceil_x, ceil_y, ImgPointDetail.powerAbilitySize, ImgPointDetail.powerAbilitySize);
                Mat roi_img = new Mat(image, rect);
                Mat tmp_img = new Mat();
                roi_img.copyTo(tmp_img);
                Imgcodecs.imwrite("D:\\var\\imgsplit\\powerAbility" + i + "_" + j + ".jpg", tmp_img);
            }
        }
        points = ImgPointDetail.sampleAbilityPoint;
        for (int i = 0; i < points.length; i++) {
            int[][] inner = points[i];
            for (int j = 0; j < inner.length; j++) {
                System.out.println(inner[j][0]);
                int ceil_x = sourceWidth * inner[j][0] / ImgPointDetail.FULL_WIDTH;
                int ceil_y = sourceHeight * inner[j][1] / ImgPointDetail.FULL_HEIGHT;
                System.out.println(ceil_x+"-"+ceil_y);
                Rect rect = new Rect(ceil_x, ceil_y, ImgPointDetail.sampleAbilitySize, ImgPointDetail.sampleAbilitySize);
                Mat roi_img = new Mat(image, rect);
                Mat tmp_img = new Mat();
                roi_img.copyTo(tmp_img);
                Imgcodecs.imwrite("D:\\var\\imgsplit\\sampleAbility" + i + "_" + j + ".jpg", tmp_img);
            }
        }
    }

}
