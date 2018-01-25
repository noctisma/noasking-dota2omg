package com.noasking.dota2.consumer.model.hive;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2017/12/20.
 */
@Data
public class HiveAbility extends AbsHiveModel implements Serializable {

    private long match_id;

    private long account_id;

    private int ability;

    private int time;

    private int level;

    @Override
    public String getAppendString() {
        StringBuffer sb = new StringBuffer();
        sb.append(match_id).append('\001')
                .append(account_id).append('\001')
                .append(ability).append('\001')
                .append(time).append('\001')
                .append(level).append('\001');
        return sb.toString();
    }
}
