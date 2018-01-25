package com.noasking.dota2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by MaJing on 2017/11/22.
 */
@Data
@Entity
@Table(name = "game_item")
public class GameItemEntity {

    @Id
    private int id;

    private String name;

    private int cost;

    private boolean secretShop;

    private boolean sideShop;

    private boolean recipe;

//    @JsonProperty(value = "localized_name")
    private String localizedName;

}
