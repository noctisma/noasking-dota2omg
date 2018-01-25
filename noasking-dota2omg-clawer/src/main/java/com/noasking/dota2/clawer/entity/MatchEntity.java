package com.noasking.dota2.clawer.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Created by MaJing on 2017/11/9.
 */
@Data
@Entity
@Table(name = "matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long matchId;

    private long matchSeqNum;

    private int gameMode;

    private String status;

    private String detail;

}
