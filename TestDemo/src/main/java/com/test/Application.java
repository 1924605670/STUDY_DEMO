package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-02 17:21
 * @Description
 **/
@SpringBootApplication(scanBasePackages = {"com"})
@EnableJpaRepositories("com.study.sb.repository")
@EntityScan("com.study.sb.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
