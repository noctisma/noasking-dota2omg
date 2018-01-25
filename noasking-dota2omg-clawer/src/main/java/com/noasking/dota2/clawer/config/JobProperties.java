package com.noasking.dota2.clawer.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MaJing on 2017/11/24.
 */
@Data
public class JobProperties {

    /**
     * 历史任务并发数
     */
    private int size;

    private List<String> keys = new ArrayList<>();

    private Random random = new Random();

    public String getKey() {
        return keys.get(random.nextInt(keys.size()));
    }


}
