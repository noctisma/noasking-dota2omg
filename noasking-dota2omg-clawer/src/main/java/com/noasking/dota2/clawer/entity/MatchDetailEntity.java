package com.noasking.dota2.clawer.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Created by MaJing on 2017/12/1.
 */
@Data
@Entity
@Table(name = "match_detail")
public class MatchDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private int radiant_win;
    private int duration;
    private int pre_game_duration;
    private int start_time;
    private int match_id;
    private int match_seq_num;
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
