package com.noasking.opencv;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.opencv.core.Core;

import java.io.IOException;

/**
 * Created by MaJing on 2018/2/1.
 */
@SpringBootApplication
public class OpenCVApplication {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SpringApplication.run(OpenCVApplication.class, args);
        loadLibrary();
    }

    private static void loadLibrary() throws NoSuchFieldException, IllegalAccessException, IOException {
        Mat image = Imgcodecs.imread("/omg_pick.jpg");
        int height = image.rows();
        int width = image.cols();
        System.out.println(image.empty());
        System.out.println("height:" + height + " width:" + width);
    }

}
