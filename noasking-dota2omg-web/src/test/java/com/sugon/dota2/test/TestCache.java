package com.sugon.dota2.test;

import com.noasking.dota2.WebApplication;
import com.noasking.dota2.repository.HeroRepository;
import com.noasking.dota2.web.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by MaJing on 2018/1/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class TestCache {


    @Autowired
    private CacheService cacheService;

    @Test
    public void testHero(){
        System.out.println(cacheService.getHeroById(11));
        System.out.println(cacheService.getHeroById(11));
    }

}
