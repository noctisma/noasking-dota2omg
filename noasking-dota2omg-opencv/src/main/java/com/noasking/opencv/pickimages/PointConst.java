package com.noasking.opencv.pickimages;

/**
 * 图片处理常量
 * Created by MaJing on 2018/1/19.
 */
public class PointConst {

    /**
     * 标准处理高度
     */
    public static final int STANDARD_ROWS = 1440;

    /**
     * 标准处理宽度
     */
    public static final int STANDARD_COLS = 2560;

    /**
     * 普通技能
     */
    public class NormalAbility {

        public static final int SIZE = 100;
    }

    /**
     * 大招
     */
    public class PowerAbility {
        public static final int SIZE = 126;

    }

    /**
     * 英雄
     */
    public class Hero {

        public static final int HeroHeight = 148;

        public static final int HeroWidth = 120;

    }


    /**
     * 定位图片路径
     */
    public static class IndexImagesPath {
        public static final String HERO_PATH = "D:\\TEMP\\aaa\\tianfu.png";

        public static final String NORMAL_ABILITY_PATH = "";

        public static final String POWER_ABILITY_PATH = "";
    }



}
