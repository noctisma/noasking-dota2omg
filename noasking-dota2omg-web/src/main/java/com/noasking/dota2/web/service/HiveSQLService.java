package com.noasking.dota2.web.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by MaJing on 2018/1/26.
 */
@Service
public class HiveSQLService {

    /**
     * 统计技能胜率排行榜
     */
    private static final String SQL_COUNT_ABILITY_RANAKNG_LIST =
            "SELECT AB1.ABILITY KEYWORD,\n" +
                    "       ROUND((CAST(AB1.COUNT1 AS DOUBLE) / AB2.COUNT1) * 100, 2) PERCEN,\n" +
                    "       AB2.COUNT1 TOTAL\n" +
                    "  FROM (SELECT COUNT(1) COUNT1, E.ABILITY\n" +
                    "          FROM (SELECT A.*\n" +
                    "                  FROM (SELECT DISTINCT MATCH_ID, ACCOUNT_ID, ABILITY\n" +
                    "                          FROM D_ABILITY D\n" +
                    "                         WHERE <cond>) A,\n" +
                    "                       (SELECT * FROM D_MATCH WHERE <cond>) B\n" +
                    "                 WHERE A.MATCH_ID = B.MATCH_ID\n" +
                    "                   AND B.RADIANT_WIN = TRUE) E\n" +
                    "         GROUP BY E.ABILITY) AB1,\n" +
                    "       (SELECT COUNT(1) COUNT1, E.ABILITY\n" +
                    "          FROM (SELECT A.*\n" +
                    "                  FROM (SELECT DISTINCT MATCH_ID, ACCOUNT_ID, ABILITY\n" +
                    "                          FROM D_ABILITY D\n" +
                    "                         WHERE <cond>) A,\n" +
                    "                       (SELECT * FROM D_MATCH WHERE <cond>) B\n" +
                    "                 WHERE A.MATCH_ID = B.MATCH_ID) E\n" +
                    "         GROUP BY E.ABILITY) AB2\n" +
                    " WHERE AB1.ABILITY = AB2.ABILITY";

    public String getSqlCountAbilityRanakngList(CountTimeType type) {
        return SQL_COUNT_ABILITY_RANAKNG_LIST.replaceAll("<cond>", getStartDtStr(type));
    }

    private String getStartDtStr(CountTimeType type) {
        String sql = " 1 = 1 ";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        switch (type) {
            case SevenDay:
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                break;
            case Month:
                calendar.add(Calendar.MONTH, -1);
                break;
            default:
                return sql;
        }
        sql = " dt >= '" + sdf.format(calendar.getTime()) + "'";
        return sql;
    }

    /**
     * 统计英雄技能排行榜
     */
    private static final String SQL_COUNT_HERO_RANAKNG_LIST =
            "SELECT AB1.ABILITY,\n" +
                    "       ROUND((CAST(AB1.COUNT1 AS DOUBLE) / AB2.COUNT1) * 100, 2),\n" +
                    "       AB2.COUNT1\n" +
                    "  FROM (SELECT COUNT(1) COUNT1, E.ABILITY\n" +
                    "          FROM (SELECT A.*\n" +
                    "                  FROM (SELECT DISTINCT MATCH_ID, ACCOUNT_ID, ABILITY\n" +
                    "                          FROM D_ABILITY D) A,\n" +
                    "                       D_MATCH B\n" +
                    "                 WHERE A.MATCH_ID = B.MATCH_ID\n" +
                    "                   AND B.RADIANT_WIN = TRUE) E\n" +
                    "         GROUP BY E.ABILITY) AB1,\n" +
                    "       (SELECT COUNT(1) COUNT1, E.ABILITY\n" +
                    "          FROM (SELECT A.*\n" +
                    "                  FROM (SELECT DISTINCT MATCH_ID, ACCOUNT_ID, ABILITY\n" +
                    "                          FROM D_ABILITY D) A,\n" +
                    "                       D_MATCH B\n" +
                    "                 WHERE A.MATCH_ID = B.MATCH_ID) E\n" +
                    "         GROUP BY E.ABILITY) AB2\n" +
                    " WHERE AB1.ABILITY = AB2.ABILITY";

    public enum CountTimeType {
        SevenDay("SEVEN_DAY"), Month("MONTH"), All("ALL");

        private String name;

        CountTimeType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
