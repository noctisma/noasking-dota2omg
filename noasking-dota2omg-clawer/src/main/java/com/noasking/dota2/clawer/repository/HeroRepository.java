package com.noasking.dota2.clawer.repository;

import com.noasking.dota2.clawer.entity.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MaJing on 2017/11/22.
 */
public interface HeroRepository extends JpaRepository<HeroEntity, Integer> {
}
