package com.noasking.dota2.clawer.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by MaJing on 2017/12/1.
 */
@Data
@Entity
@Table(name = "ability_upgrade")
public class AbilityUpgradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long match_id;

    private long account_id;

    private int ability;

    private int time;

    private int level;

}
