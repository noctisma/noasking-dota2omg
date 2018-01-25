package com.noasking.opencv;

import lombok.Getter;

/**
 * 标准点位信息
 * Created by MaJing on 2018/1/23.
 */
public class ImgPointDetail {

    public static final int FULL_WIDTH = 1440;
    public static final int FULL_HEIGHT = 900;

    public static final int[][][] heroPoint = {
            {{125, 145}, {1230, 145}},
            {{125, 260}, {1230, 260}},
            {{125, 395}, {1230, 395}},
            {{125, 530}, {1230, 530}},
            {{125, 660}, {1230, 660}}
    };

    public static final int heroHeight = 85;
    public static final int heroWidth = 75;


    /**
     * 大招点位信息
     */
    public static final int[][][] powerAbilityPoint = {
            {{495, 135}, {575, 135}, {655, 135}, {735, 135}, {815, 135}, {895, 135}},
            {{500, 215}, {575, 215}, {655, 215}, {735, 215}, {830, 215}, {895, 215}}
    };

    public static final int powerAbilitySize = 55;

    /**
     * 普通技能点位信息
     */
    public static final int[][][] sampleAbilityPoint = {
            {{525, 285}, {590, 285}, {655, 285}, {735, 285}, {800, 285}, {865, 285}},
            {{525, 340}, {590, 340}, {655, 340}, {735, 340}, {800, 340}, {865, 340}},
            {{525, 395}, {590, 395}, {655, 395}, {735, 395}, {800, 395}, {865, 395}},
            {{525, 495}, {590, 495}, {655, 495}, {735, 495}, {800, 495}, {865, 495}},
            {{525, 560}, {590, 560}, {655, 560}, {735, 560}, {800, 560}, {865, 560}},
            {{525, 630}, {590, 630}, {655, 630}, {735, 630}, {800, 630}, {865, 630}}
    };

    public static final int sampleAbilitySize = 55;
}
