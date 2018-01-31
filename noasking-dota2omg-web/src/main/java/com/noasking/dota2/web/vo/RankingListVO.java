package com.noasking.dota2.web.vo;

import lombok.Data;

/**
 * 排行榜视图VO
 * Created by MaJing on 2018/1/29.
 */
@Data
public class RankingListVO {

    /**
     * 标识姓名，一般为英文
     */
    private String name;
    /**
     * 本地化名称
     */
    private String localizedName;
    /**
     * 胜率
     */
    private float percent;
    /**
     * 总数
     */
    private long total;

}
