package com.noasking.dota2.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by MaJing on 2017/12/1.
 */
@SpringBootApplication
public class Dota2ConsumerApplication {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext app = SpringApplication.run(Dota2ConsumerApplication.class, args);

    }

}
