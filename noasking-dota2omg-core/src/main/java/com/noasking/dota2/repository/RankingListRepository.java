package com.noasking.dota2.repository;

import com.noasking.dota2.entity.RankingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MaJing on 2018/1/25.
 */
public interface RankingListRepository extends JpaRepository<RankingListEntity,Long> {
}
