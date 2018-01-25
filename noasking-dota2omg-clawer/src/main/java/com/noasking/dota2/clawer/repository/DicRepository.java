package com.noasking.dota2.clawer.repository;

import com.noasking.dota2.clawer.entity.DicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by MaJing on 2017/11/10.
 */
public interface DicRepository extends JpaRepository<DicEntity, Long> {

    DicEntity findByKey2(String key2);

    @Query(value = "select * from ",nativeQuery = true)
    long getMinMatchId();

}
