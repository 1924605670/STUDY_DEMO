package com.study.rabbit.config;

import com.study.rabbit.produce.MessageProduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringAfter implements ApplicationRunner {

    @Autowired
    private MessageProduce messageProduce;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        messageProduce.sendMessage();
    }
}
