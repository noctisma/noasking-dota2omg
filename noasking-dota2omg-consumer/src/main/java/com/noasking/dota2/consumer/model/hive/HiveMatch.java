package com.noasking.dota2.consumer.model.hive;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by MaJing on 2017/12/20.
 */
@Data
public class HiveMatch extends AbsHiveModel implements Serializable {

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

    @Override
    public String getAppendString() {
        StringBuffer sb = new StringBuffer();
        sb.append(radiant_win).append('\001')
                .append(duration).append('\001')
                .append(pre_game_duration).append('\001')
                .append(start_time).append('\001')
                .append(match_id).append('\001')
                .append(match_seq_num).append('\001')
                .append(tower_status_radiant).append('\001')
                .append(tower_status_dire).append('\001')
                .append(barracks_status_radiant).append('\001')
                .append(barracks_status_dire).append('\001')
                .append(cluster).append('\001')
                .append(first_blood_time).append('\001')
                .append(lobby_type).append('\001')
                .append(human_players).append('\001')
                .append(leagueid).append('\001')
                .append(positive_votes).append('\001')
                .append(negative_votes).append('\001')
                .append(game_mode).append('\001')
                .append(flags).append('\001')
                .append(engine).append('\001')
                .append(radiant_score).append('\001')
                .append(dire_score).append('\001');
        return sb.toString();
    }
}
