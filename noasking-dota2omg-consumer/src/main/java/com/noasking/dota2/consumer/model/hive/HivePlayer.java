package com.noasking.dota2.consumer.model.hive;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2017/12/20.
 */
@Data
public class HivePlayer extends AbsHiveModel implements Serializable {

    private long match_id;
    private long account_id;
    private int player_slot;
    private int hero_id;
    private int item_0;
    private int item_1;
    private int item_2;
    private int item_3;
    private int item_4;
    private int item_5;
    private int backpack_0;
    private int backpack_1;
    private int backpack_2;
    private int kills;
    private int deaths;
    private int assists;
    private int leaver_status;
    private int last_hits;
    private int denies;
    private int gold_per_min;
    private int xp_per_min;
    private int level;
    private int hero_damage;
    private int tower_damage;
    private int hero_healing;
    private int gold;
    private int gold_spent;
    private int scaled_hero_damage;
    private int scaled_tower_damage;
    private int scaled_hero_healing;

    @Override
    public String getAppendString() {
        StringBuffer sb = new StringBuffer();
        sb.append(match_id).append('\001')
                .append(account_id).append('\001')
                .append(player_slot).append('\001')
                .append(hero_id).append('\001')
                .append(item_0).append('\001')
                .append(item_1).append('\001')
                .append(item_2).append('\001')
                .append(item_3).append('\001')
                .append(item_4).append('\001')
                .append(item_5).append('\001')
                .append(backpack_0).append('\001')
                .append(backpack_1).append('\001')
                .append(backpack_2).append('\001')
                .append(kills).append('\001')
                .append(deaths).append('\001')
                .append(assists).append('\001')
                .append(leaver_status).append('\001')
                .append(last_hits).append('\001')
                .append(denies).append('\001')
                .append(gold_per_min).append('\001')
                .append(xp_per_min).append('\001')
                .append(level).append('\001')
                .append(hero_damage).append('\001')
                .append(tower_damage).append('\001')
                .append(hero_healing).append('\001')
                .append(gold).append('\001')
                .append(gold_spent).append('\001')
                .append(scaled_hero_damage).append('\001')
                .append(scaled_tower_damage).append('\001')
                .append(scaled_hero_healing).append('\001');
        return sb.toString();
    }
}
