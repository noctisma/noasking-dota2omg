package com.noasking.dota2.web.service;

import com.noasking.dota2.entity.AbilityEntity;
import com.noasking.dota2.entity.GameItemEntity;
import com.noasking.dota2.entity.HeroEntity;
import com.noasking.dota2.repository.AbilityRepository;
import com.noasking.dota2.repository.GameItemRepository;
import com.noasking.dota2.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by MaJing on 2018/1/29.
 */
@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private AbilityRepository abilityRepository;

    @Autowired
    private GameItemRepository gameItemRepository;

    /**
     * 根据ID获取英雄信息
     *
     * @param id
     * @return
     */
    @Cacheable(value = "hero", key = "#id")
    public HeroEntity getHeroById(final Integer id) {
        HeroEntity result = heroRepository.findOne(id);
//        if (result == null) {
//            result = new HeroEntity();
//            result.setId(id);
//        }
        return result;
    }

    /**
     * 移除所有英雄缓存
     */
    @CacheEvict(value = "hero", allEntries = true)
    public void removeAllHeroCache() {
        logger.info("英雄缓存已经移除");
    }

    /**
     * 根据技能ID获取技能信息
     *
     * @param id
     * @return
     */
    @Cacheable(value = "ability", key = "#id")
    public AbilityEntity getAbilityById(final Integer id) {
        AbilityEntity result = abilityRepository.findOne(id);
//        if (result == null) {
//            result = new AbilityEntity();
//            result.setId(id);
//        }
        return result;
    }

    /**
     * 移除所有技能信息
     */
    @CacheEvict(value = "ability", allEntries = true)
    public void removeAllAbilityCache() {
        logger.info("技能缓存已经移除");
    }

    /**
     * 根据游戏物品ID获取游戏物品信息
     *
     * @param id
     * @return
     */
    @Cacheable(value = "gameItem", key = "#id")
    public GameItemEntity getGameItemById(final Integer id) {
        GameItemEntity result = gameItemRepository.findOne(id);
        if (result == null) {
            result = new GameItemEntity();
            result.setId(id);
        }
        return result;
    }

    /**
     * 移除所有的物品信息
     */
    @CacheEvict(value = "gameItem", allEntries = true)
    public void removeGameItemCache() {
        logger.info("游戏物品缓存已经移除");
    }


}
