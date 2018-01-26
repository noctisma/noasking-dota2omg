package com.noasking.dota2.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Created by MaJing on 2017/11/22.
 */
@Data
@Entity
@Table(name = "hero")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String localizedName;

}
