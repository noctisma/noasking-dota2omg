package com.noasking.dota2.clawer.repository;

import com.noasking.dota2.clawer.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MaJing on 2017/12/1.
 */
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
