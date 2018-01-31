package com.noasking.dota2.repository;

import com.noasking.dota2.entity.GameItemEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 游戏物品
 * Created by MaJing on 2017/11/22.
 */
public interface GameItemRepository extends JpaRepository<GameItemEntity, Integer>{


}
