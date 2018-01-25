package com.noasking.dota2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by MaJing on 2018/1/25.
 */
@Data
@Entity
@Table(name = "ability")
public class RankingListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;
    private Date updateTime;
    private String keyword;

    private float percent;
    private long total;
    private String param1;
    private String param2;
    private String param3;


}
