package com.noasking.dota2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by MaJing on 2018/1/25.
 */
@Entity
@Table(name = "ability")
@Data
public class AbilityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String localizedName;

}
