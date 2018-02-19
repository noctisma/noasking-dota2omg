package com.noasking.opencv.pickimages;

import com.noasking.opencv.utils.TempleteMatch;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 图片选择
 * Created by MaJing on 2018/2/8.
 */
public class ImageAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(ImageAnalysis.class);

    /**
     * 传入图片输入流，分析图片，范围具体的技能信息
     *
     * @param src 上传的图片
     * @return
     */
    public static PickImageDetail analysis(Mat src) {
        // 图片处理：灰化、重设置大小
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY, 0);
        Imgproc.resize(src, src, new Size(PointConst.STANDARD_COLS, PointConst.STANDARD_ROWS), 0, 0, Imgproc
                .INTER_AREA);






        return null;

    }



    private Point[][] rectPowerAbility(Mat src) {
        // 大招定位
        Point[][] powerPoints = new Point[2][6];
        Mat powerAbility = Imgcodecs.imread(PointConst.IndexImagesPath.POWER_ABILITY_PATH);
        Imgproc.cvtColor(powerAbility, powerAbility, Imgproc.COLOR_BGR2GRAY, 0);
        Point point = TempleteMatch.singleTempleteMatcher(src, powerAbility);
        int x = powerAbility.cols() / 2 + Integer.parseInt(new java.text.DecimalFormat("0").format(point.x));
        int y = Integer.parseInt(new java.text.DecimalFormat("0").format(point.y)) + powerAbility.rows() / 2;
        // 根据定位情况及技能像素进行拆分 拆分成二位数组，然后尽心
        for (int i = 0; i < powerPoints.length; i++) {
            Point[] inner = powerPoints[i];
            for (int j = 0; j < inner.length; j++) {
                Point point_ = new Point(x + PointConst.PowerAbility.SIZE * (j - 3), y + PointConst.PowerAbility.SIZE
                        * (i - 1));
                powerPoints[i][j] = point_;
            }
        }
        return powerPoints;
    }

    private Point[][] rectNormalAbility(Mat src) {
        // 普通节能定位
        Point[][] normalPoints = new Point[6][6];
        Mat normalAbility = Imgcodecs.imread(PointConst.IndexImagesPath.POWER_ABILITY_PATH);
        Imgproc.cvtColor(normalAbility, normalAbility, Imgproc.COLOR_BGR2GRAY, 0);
        Point point = TempleteMatch.singleTempleteMatcher(src, normalAbility);
        int x = normalAbility.cols() / 2 + Integer.parseInt(new java.text.DecimalFormat("0").format(point.x));
        int y = Integer.parseInt(new java.text.DecimalFormat("0").format(point.y)) + normalAbility.rows() / 2;
        for (int i = 0; i < normalPoints.length; i++) {
            Point[] inner = normalPoints[i];
            for (int j = 0; j < inner.length; j++) {
                Point point_ = new Point(x + PointConst.NormalAbility.SIZE * (j - 3), y + PointConst.NormalAbility.SIZE
                        * (i - 1));
                normalPoints[i][j] = point_;
            }
        }
        return normalPoints;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        analysis(Imgcodecs.imread("D:\\TEMP\\aaa\\20180130211723_1.jpg"));
    }


}
