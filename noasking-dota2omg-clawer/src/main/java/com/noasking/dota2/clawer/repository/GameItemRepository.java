package com.noasking.dota2.clawer.repository;

import com.noasking.dota2.clawer.entity.GameItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 游戏物品
 * Created by MaJing on 2017/11/22.
 */
public interface GameItemRepository extends JpaRepository<GameItemEntity, Integer>{


}
