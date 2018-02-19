package com.noasking.opencv.compare;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.noasking.opencv.ImageViewer;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgproc.Imgproc;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Created by MaJing on 2018/1/24.
 */
public class CompareUtils2 {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private JFrame frmjavaSwing;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    CompareUtils2 window = new CompareUtils2();
//                    window.frmjavaSwing.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        final JSlider slider_method2 = new JSlider();
////
//        slider_method2.setValue(0);
//        slider_method2.setMaximum(5);
//        slider_method2.setBounds(388, 10, 150, 25);
//        final JLabel label_method2 = new JLabel("0");
//        label_method2.setBounds(548, 10, 46, 15);
//        label_method2.setText(slider_method2.getValue() + "");
        Mat src = Imgcodecs.imread("D:\\TEMP\\aaa\\20180130211723_1.jpg");
        Mat template = Imgcodecs.imread("D:\\images\\ability_lg\\6818lg.png");
        Imgproc.resize(template, template, new Size(80,80), 0, 0, Imgproc.INTER_AREA);
        System.out.println(src.empty());
        CompareUtils2 compareUtils2 = new CompareUtils2();
        Mat image = compareUtils2.findTemplete(src, template,  Imgproc.TM_SQDIFF_NORMED);
        ImageViewer imageViewer = new ImageViewer(image, "第一幅图片");
        imageViewer.imshow();
//        Imgcodecs.imwrite(dstImg, img);

    }

    public Mat findTemplete(Mat src, Mat template, int match_method) {

        int result_cols = src.cols() - template.cols() + 1;
        int result_rows = src.rows() - template.rows() + 1;
        System.out.println(src.cols() + ":" + src.rows());
        System.out.println(template.cols() + ":" + template.rows());
        System.out.println("result_cols="+result_cols+",result_rows="+result_rows);
        Mat srcClone = src.clone();
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
        Imgproc.matchTemplate(src, template, result, match_method);


        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        //matchTemplate有找到用綠色框表示
        Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(0, 255, 0));


        //System.out.println( matchLoc.y+" ,"+(matchLoc.y + template.rows())+","+matchLoc.x+","+(matchLoc.x +
        // template.cols()));
        //擷取ROI區
        Mat subSrc = srcClone.submat((int) matchLoc.y, (int) (matchLoc.y + template.rows()), (int) matchLoc.x, (int)
                (matchLoc.x + template.cols()));
        //Imgcodecs.imwrite("D:\\WorkSpace\\Project\\opencv3.1\\samples\\test-result.jpg", subSrc);

        Mat img1Hist = getHistogram(template);
        Mat img2Hist = getHistogram(subSrc);
        double resultCompareHist = Imgproc.compareHist(img1Hist, img2Hist, 3);

        //比對結果
        if (resultCompareHist < 0.3) {
            //使用compareHist<0.3就有符合,則畫出黑框
            Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()),
                    new Scalar(0, 0, 0), 3);
        }


        System.out.println("result0="+resultCompareHist);

        return src;
    }


    public Mat getHistogram(Mat source) {

        Mat HSVsource = new Mat();
        //BGR轉換到HSV colorspace
        Imgproc.cvtColor(source, HSVsource, Imgproc.COLOR_BGR2HSV);

        Mat hist = new Mat();
        int Hbins = 30;
        int Sbins = 32;

        //對Hue channel使用30個bins,對Saturation chhannel使用32個bins
        MatOfInt mHistSize = new MatOfInt(Hbins, Sbins);

        //對Hue的取值Range(0,256),Saturation取值Range:(0,180)
        MatOfFloat mRanges = new MatOfFloat(0, 180, 0, 256);

        //使用chnnel0 及channel1
        MatOfInt mChannels = new MatOfInt(0, 1);

        List<Mat> Lhsv = Arrays.asList(HSVsource);

        //計算HSV色彩空間的直方圖
        Imgproc.calcHist(Lhsv, mChannels, new Mat(), hist, mHistSize, mRanges, false);
        Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX, -1, new Mat());

        return hist;
    }


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
}
