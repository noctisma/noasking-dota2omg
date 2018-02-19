package com.noasking.opencv.pickimages;

import com.noasking.opencv.utils.TempleteMatch;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by MaJing on 2018/2/11.
 */
public class HeroImageAnalysis extends AbstractImageAnalysis {

    private String allHeroPath = "D:\\images\\hero_vert";

    @Override
    public int[][] analysis(Mat src) {
        Mat src1 = src.clone();
        Map<Integer, Integer> value = new HashMap<>(10);
        Point[][] points = reatHero(src);
        Mat[][] mats = new Mat[2][5];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Mat temp = new Mat(src1, new Rect(new Double(points[i][j].x).intValue() - i*40, new Double(points[i][j].y)
                        .intValue() - 140, PointConst.Hero.HeroWidth, PointConst.Hero.HeroHeight));
                Mat img1Hist=getHistogram(temp);
                Mat img2Hist=getHistogram(Imgcodecs.imread("D:\\TEMP\\aaa\\32.jpg"));
                double result0=Imgproc.compareHist(img1Hist, img2Hist,3);
                System.out.println(i + "_" + j +":"+ result0);
                Imgcodecs.imwrite("D:\\TEMP\\aaa\\hero\\" + i + "_" + j + ".jpg", temp);
                mats[i][j] = temp;
            }
        }
        return null;
    }

    /**
     * 返回点位是List，需要拆分成是个点，如果在区间之内，否则
     *
     * @param pointList
     * @return
     */
    private static Point[][] locationPointList(List<Point> pointList) {
        Point[][] pre_result = new Point[2][5];
        Map<Integer, Integer> resultMap = new TreeMap<>();
        for (Point point : pointList) {
            int x = Integer.parseInt(new java.text.DecimalFormat("0").format(point.x));
            int y = Integer.parseInt(new java.text.DecimalFormat("0").format(point.y));
            int x_index;
            if (x > 311 && x < 411) {
                x_index = 0;
            } else if (x > 2103 && x < 2203) {
                x_index = 1;
            } else {
                continue;
            }
            int y_index;
            if (y > 300 && y < 400) {
                y_index = 0;
            } else if (y > 500 && y < 600) {
                y_index = 1;
            } else if (y > 700 && y < 800) {
                y_index = 2;
            } else if (y > 950 && y < 1050) {
                y_index = 3;
            } else if (y > 1150 && y < 1250) {
                y_index = 4;
            } else {
                continue;
            }
            pre_result[x_index][y_index] = point;
        }
        return pre_result;
    }


    private Point[][] reatHero(Mat src) {
        // 英雄定位,
        Mat hero = Imgcodecs.imread(PointConst.IndexImagesPath.HERO_PATH);
        Imgproc.cvtColor(hero, hero, Imgproc.COLOR_BGR2GRAY, 0);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY, 0);
        List<Point> heroPointList = TempleteMatch.mutliTempleteMatcher(src, hero, 10);
        // 截取
        if (heroPointList != null && heroPointList.size() != 0) {
            //
            Point[][] heroPointArray = locationPointList(heroPointList);
            for (int i = 0; i < heroPointArray.length; i++) {
                Point[] a = heroPointArray[i];
                for (int j = 0; j < a.length; j++) {
                    System.out.println(a[j]);
                    System.out.println( i + "-" + j + ":" + a[j].x + "," + a[j].y);
                }
            }
            return heroPointArray;
        }
        return new Point[2][5];
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        AbstractImageAnalysis abstractImageAnalysis = new HeroImageAnalysis();
        abstractImageAnalysis.analysis(Imgcodecs.imread("D:\\TEMP\\aaa\\20180130211723_1.jpg"));
    }
}
