package com.noasking.dota2.clawer.model.player;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2017/11/9.
 */
@Data
public class AbilityUpgrade implements Serializable {

    private int ability;

    private int time;

    private int level;

}
