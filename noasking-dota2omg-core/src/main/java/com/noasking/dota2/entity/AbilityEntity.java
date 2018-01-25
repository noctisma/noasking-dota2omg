package com.noasking.dota2.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MaJing on 2018/1/25.
 */
@Data
@Entity
@Table(name = "ability")
public class AbilityEntity {

    @Id
    private long id;

    private String name;
    private String localizedName;

}
