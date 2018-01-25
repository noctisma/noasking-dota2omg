package com.noasking.dota2.clawer.repository;

import com.noasking.dota2.clawer.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by MaJing on 2017/11/9.
 */
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    @Query(value = "select min(match_id) from matches",nativeQuery = true)
    long getMinMatchId();

    @Query(value = "select max(match_seq_num) from matches",nativeQuery = true)
    long getMaxSeqNum();

    @Query(value = "select * from matches where status = 'U' limit 10",nativeQuery = true)
    List<MatchEntity> getUseMatchs();

}
