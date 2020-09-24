package com.study.rabbit;

import com.study.rabbit.beans.DsTfcpassAllEntity;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-29 10:15
 * @Description
 **/
@SpringBootApplication
@EnableRabbit
@EnableAdminServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        Map<String, List<DsTfcpassAllEntity>> map = new HashMap<>();
//        List<DsTfcpassAllEntity> ds =new ArrayList<>();
//        DsTfcpassAllEntity dst = new DsTfcpassAllEntity();
//        dst.setAcceptTime(new Date());
//        ds.add(dst);
//        map.put("1",ds);
//        map.get("1").add(dst);
//        List<DsTfcpassAllEntity> dsTfcpassAllEntities = map.get("1");
//        DsTfcpassAllEntity dst2 = new DsTfcpassAllEntity();
//        dst2.setAcceptTime(new Date());
//        dsTfcpassAllEntities.add(dst2);
//        map.put("1",dsTfcpassAllEntities);
//        System.out.println(map.get("1").size());
    }
}
