package com.sugon.dota2.test;

import com.noasking.dota2.WebApplication;
import com.noasking.dota2.web.service.HeroParseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by MaJing on 2018/2/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class TestSyncHero {

    @Autowired
    private HeroParseService heroParseService;

    @Test
    public void testDownloadAbilityImages() throws IOException {
//        URLImageDownload.download("http://cdn.dota2.com/apps/dota2/images/abilities/centaur_hoof_stomp_lg.png", WebConst.ImagePath.ABILITY + "11.png");
        System.out.println(heroParseService.downloadAbilityImages(null));
    }

}
