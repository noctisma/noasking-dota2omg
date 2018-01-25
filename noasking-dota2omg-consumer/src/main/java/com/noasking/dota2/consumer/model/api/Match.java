package com.noasking.dota2.consumer.model.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MaJing on 2017/11/9.
 */
@Data
public class Match implements Serializable {

    private List<Player> players;

    private boolean radiant_win;
    private int duration;
    private int pre_game_duration;
    private long start_time;
    private long match_id;
    private long match_seq_num;
    private int tower_status_radiant;
    private int tower_status_dire;
    private int barracks_status_radiant;
    private int barracks_status_dire;
    private int cluster;
    private int first_blood_time;
    private int lobby_type;
    private int human_players;
    private int leagueid;
    private int positive_votes;
    private int negative_votes;
    private int game_mode;
    private int flags;
    private int engine;
    private int radiant_score;
    private int dire_score;

}
