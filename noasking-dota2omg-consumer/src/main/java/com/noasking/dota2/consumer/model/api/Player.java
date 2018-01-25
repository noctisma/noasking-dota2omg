package com.noasking.dota2.consumer.model.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MaJing on 2017/11/9.
 */
@Data
public class Player implements Serializable {
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
    private List<AbilityUpgrade> ability_upgrades;
    private List<AdditionalUnit> additional_units;
}
