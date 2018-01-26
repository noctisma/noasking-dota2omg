package com.noasking.dota2.web.service;

import com.noasking.dota2.entity.RankingListEntity;
import com.noasking.dota2.repository.RankingListRepository;
import com.noasking.dota2.web.utils.hive.HiveJdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MaJing on 2018/1/26.
 */
@Service
public class RankingListService {

    @Autowired
    private HiveJdbcUtil hiveJdbcUtil;

    @Autowired
    private HiveSQLService hiveSQLService;

    @Autowired
    private RankingListRepository rankingListRepository;

    /**
     * 排行榜统计类型
     */
    public static class RankingListType {

        public static final String HERO_SEVEN_DAY = "HERO_SEVEN_DAY";

    }

    /**
     * 统计技能排行榜
     *
     * @return
     */
    @Transactional
    public boolean countAbilityRankingList(HiveSQLService.CountTimeType type) throws Exception {
        String sql = hiveSQLService.getSqlCountAbilityRanakngList(type);
        List<Map<String, Object>> sqlResult = hiveJdbcUtil.querySqlForKeyValue(sql);
        if (sqlResult == null || sqlResult.size() == 0) {
            return false;
        }
        List<RankingListEntity> rankingListEntities = new ArrayList<>(sqlResult.size());
        for (Map<String, Object> bean : sqlResult) {
            RankingListEntity rankingListEntity = new RankingListEntity();
            rankingListEntity.setCode("ABILITY_"+type);
            rankingListEntity.setKeyword(String.valueOf(bean.get("keyword")));
            rankingListEntity.setPercent(Float.parseFloat(String.valueOf(bean.get("percen"))));
            rankingListEntity.setTotal(Long.parseLong(String.valueOf(bean.get("total"))));
            rankingListEntities.add(rankingListEntity);
        }
        rankingListRepository.save(rankingListEntities);
        return true;
    }

    @Transactional
    public boolean countHeroRankingList() {


        return true;
    }


}
