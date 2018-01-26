package com.sugon.dota2.test;

import com.noasking.dota2.WebApplication;
import com.noasking.dota2.web.service.HiveSQLService;
import com.noasking.dota2.web.service.RankingListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static com.noasking.dota2.web.service.HiveSQLService.CountTimeType.*;

/**
 * Created by MaJing on 2018/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class TestHiveJob {

    @Autowired
    private RankingListService rankingListService;

    @Test
    public void testAbilityRankingList() throws Exception {
        rankingListService.countAbilityRankingList(HiveSQLService.CountTimeType.All);
    }

    @Test
    public void testEnumSwitch() {
        System.out.println(HiveSQLService.CountTimeType.valueOf("ALL"));
    }


}
