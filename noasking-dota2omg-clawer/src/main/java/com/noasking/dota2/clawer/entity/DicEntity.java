package com.noasking.dota2.clawer.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by MaJing on 2017/11/10.
 */
@Data
@Entity
@Table(name = "dic")
public class DicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String key2;

    private String value;

    private String remarks;

    private String status;

}
