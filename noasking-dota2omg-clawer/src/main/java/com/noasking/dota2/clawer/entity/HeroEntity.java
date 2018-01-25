package com.noasking.dota2.clawer.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by MaJing on 2017/11/22.
 */
@Data
@Entity
@Table(name = "hero")
public class HeroEntity {

    @Id
    private int id;

    private String name;

    private String localizedName;

}
