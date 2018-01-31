package com.noasking.dota2.repository;

import com.noasking.dota2.entity.AbilityEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MaJing on 2018/1/25.
 */
public interface AbilityRepository extends JpaRepository<AbilityEntity, Integer> {
}
