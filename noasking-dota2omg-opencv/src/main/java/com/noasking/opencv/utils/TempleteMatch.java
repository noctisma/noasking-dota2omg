package com.noasking.opencv.utils;

import com.noasking.opencv.ImageViewer;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板匹配
 * Created by MaJing on 2018/2/8.
 */
public class TempleteMatch {

    private static final Logger logger = LoggerFactory.getLogger(TempleteMatch.class);

    private static final int MATCH_METHOD = Imgproc.TM_SQDIFF_NORMED;

    /**
     * 模板匹配，返回单个点位信息
     *
     * @return
     */
    public static Point singleTempleteMatcher(Mat src, Mat template) {
        int result_cols = src.cols() - template.cols() + 1;
        int result_rows = src.rows() - template.rows() + 1;
        logger.debug("SRC-WIDTH:" + src.cols() + ";HEIGHT:" + src.rows());
        logger.debug("TEMPLETE-WIDTH:" + template.cols() + ";HEIGHT:" + template.rows());
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
        // 绘画
        Imgproc.matchTemplate(src, template, result, MATCH_METHOD);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        if (MATCH_METHOD == Imgproc.TM_SQDIFF || MATCH_METHOD == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }
        if (logger.isDebugEnabled()) {
            logger.debug(matchLoc.x + "," + matchLoc.y);
        }
        return matchLoc;
    }

    /**
     * 模板匹配，返回多个点位信息
     *
     * @return
     */
    public static List<Point> mutliTempleteMatcher(Mat src, Mat template, int size) {
        List<Point> pointList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Point matchLoc = singleTempleteMatcher(src, template);
            pointList.add(matchLoc);
            if (i + 1 != size) {
                //将最近一个最小值点附近模板宽度和高度的都设置为最大值防止产生干扰
                Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()),
                        new Scalar(0, 0, 0), Math.min(template.cols(), template.rows()));
            }
        }
        return pointList;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("D:\\TEMP\\aaa\\omg_pick.jpg");
//        Mat template = Imgcodecs.imread("D:\\TEMP\\aaa\\tianfu.png");
//        Mat template = Imgcodecs.imread("D:\\TEMP\\aaa\\big_arrt.png");
        Mat template = Imgcodecs.imread("D:\\TEMP\\aaa\\normal_arrt.png");
        Point matchLoc = singleTempleteMatcher(src, template);
        System.out.println(matchLoc.x + " ," + matchLoc.y);

//        List<Point> points = mutliTempleteMatcher(src, template, 10);
//        for (Point point : points) {
//            System.out.println(point.x + " ," + point.y);
//        }

        // 绘画
        Mat dstImage = new Mat();
        Imgproc.cvtColor(src, dstImage, Imgproc.COLOR_BGR2GRAY, 0);
        ImageViewer imageViewer = new ImageViewer(dstImage, "第一幅图片");
        imageViewer.imshow();
    }

}
