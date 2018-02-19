package com.noasking.opencv.pickimages;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.List;

/**
 * 抽象图片解析类
 * Created by MaJing on 2018/2/11.
 */
public abstract class AbstractImageAnalysis {

    /**
     * 返回对应的ID信息，即图片的名称
     */
    public abstract int[][] analysis(Mat src);

    public Mat findTemplete(Mat src,Mat template, int match_method){

        int result_cols = src.cols() - template.cols() + 1;
        int result_rows = src.rows() - template.rows() + 1;
        //System.out.println("result_cols="+result_cols+",result_rows="+result_rows);
        Mat srcClone=src.clone();
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
        Imgproc.matchTemplate(src, template, result, match_method);
        Core.normalize(result, result, 0, 255, Core.NORM_MINMAX, -1, new Mat());
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        //matchTemplate有找到用綠色框表示
        Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(0, 255, 0));


        //System.out.println( matchLoc.y+" ,"+(matchLoc.y + template.rows())+","+matchLoc.x+","+(matchLoc.x + template.cols()));
        //擷取ROI區
        Mat subSrc=srcClone.submat((int) matchLoc.y ,(int)(matchLoc.y + template.rows()),(int)matchLoc.x, (int)(matchLoc.x + template.cols()));
        //Imgcodecs.imwrite("C://opencv3.1//samples//test-result.jpg", subSrc);

        Mat img1Hist=getHistogram(template);
        Mat img2Hist=getHistogram(subSrc);
        double resultCompareHist=Imgproc.compareHist(img1Hist, img2Hist, 3);

        //比對結果
        if(resultCompareHist<0.3){
            //使用compareHist<0.3就有符合,則畫出黑框
            Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + template.cols(),matchLoc.y + template.rows()), new Scalar(0, 0, 0),3);
        }


        //System.out.println("result0="+result0);

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



}
