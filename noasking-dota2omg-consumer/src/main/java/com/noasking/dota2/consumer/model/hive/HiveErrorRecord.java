package com.noasking.dota2.consumer.model.hive;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2017/12/20.
 */
@Data
public class HiveErrorRecord extends AbsHiveModel implements Serializable{

    private String errorMsg;

    private String record;


    @Override
    public String getAppendString() {
        StringBuffer sb = new StringBuffer();
        sb.append(errorMsg).append('\001')
                .append(record).append('\001');
        return sb.toString();
    }
}
