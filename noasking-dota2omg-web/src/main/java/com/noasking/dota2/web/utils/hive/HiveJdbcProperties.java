package com.noasking.dota2.web.utils.hive;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaJing on 2018/1/17.
 */
@Data
@Component
@ConfigurationProperties(prefix="hive.thrift")
public class HiveJdbcProperties {

    private String username;

    private String password;

    private String driver = "org.apache.hive.jdbc.HiveDriver";

    private List<String> urls = new ArrayList<>();

}
