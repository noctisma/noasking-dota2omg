package com.noasking.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 图片对比
 * Created by MaJing on 2018/1/12.
 */
public class ImageCompare {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static double compare(Mat source, Mat target) {
        //return Imgproc.compareHist();
        return 1.0D;
    }


}
