package com.noasking.opencv;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.InflaterInputStream;

/**
 * Created by MaJing on 2018/1/10.
 */
public class FirstOpenCV {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
    }

    public static void main(String[] args) {
        Mat image = Imgcodecs.imread("D:\\WorkSpace\\GitMine\\noasking-dota2omg\\noasking-dota2omg-opencv\\src\\main" +
                "\\resources\\omg_pick.jpg");
        int height = image.rows();
        int width = image.cols();
        System.out.println(image.empty());
        System.out.println("height:" + height + " width:" + width);
        //ImageSplit.split(image);
        Rect rect = new Rect(500,215 , 55, 55); // 945 1800
        Mat roi_img = new Mat(image, rect);
        Mat tmp_img = new Mat();
        roi_img.copyTo(tmp_img);
//        ImageViewer imageViewer = new ImageViewer(tmp_img, "第一幅图片");
//        imageViewer.imshow();
        ImageSplit.split(image);
//        ImageIO.read(new FileInputStream("aaa")).


//        System.out.println(image);
//
//        int height = image.rows();
//        int width = image.cols();
//        System.out.println(image.empty());
//        System.out.println("height:" + height + " width:" + width);
//
//        ImageViewer imageViewer = new ImageViewer(image, "第一幅图片");
//        imageViewer.imshow();
//        int m = 2;
//        int n = 2;
//
//
//        int ceil_height = height / m;
//        int ceil_width = width / n;
//        System.out.println("ceil_height:" + ceil_height + " ceil_width:" + ceil_width);
//
//        String filename = "D:\\WorkSpace\\GitMine\\noasking-dota2omg\\noasking-dota2omg-opencv\\src\\main" +
//                "\\resources\\";
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                int a = j * ceil_width;
//                int b = i * ceil_height;
//                System.out.println(a + "," + b + "," + ceil_width + "," + ceil_height);
//                Rect rect = new Rect(j * ceil_width, i * ceil_height, ceil_width, ceil_height);
//                Mat roi_img = new Mat(image, rect);
//                Mat tmp_img = new Mat();
//
//                roi_img.copyTo(tmp_img);
//                Imgcodecs.imwrite(filename + i + "_" + j + ".jpg", tmp_img);
//            }
//        }
    }



}


