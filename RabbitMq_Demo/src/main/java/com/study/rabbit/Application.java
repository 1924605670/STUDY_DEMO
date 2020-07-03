package com.study.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-29 10:15
 * @Description
 **/
@SpringBootApplication
@EnableRabbit
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
