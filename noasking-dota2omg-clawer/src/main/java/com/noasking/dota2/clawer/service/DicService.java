package com.noasking.dota2.clawer.service;

import com.noasking.dota2.clawer.api.ApiConst;
import com.noasking.dota2.entity.DicEntity;
import com.noasking.dota2.repository.DicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by MaJing on 2017/11/30.
 */
@Service
public class DicService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DicRepository dicRepository;

    /**
     * 获取未执行的任务（状态为C）
     */
    @Transactional
    public DicEntity getUnlockDic() {
        String sql = " select * from dic where status = ? limit 1 for update ";
        RowMapper<DicEntity > rm = new BeanPropertyRowMapper(DicEntity .class);
        DicEntity dicEntity = jdbcTemplate.queryForObject(sql, new Object[]{ApiConst.DicState.CREATE}, rm);
        dicEntity.setStatus(ApiConst.DicState.USE);
        dicRepository.save(dicEntity);
        return dicEntity;
    }

    /**
     * 解析技能文件入库
     * @param filename
     * @return
     */
    public boolean parseAbility(String filename) {
        return true;
    }


}
