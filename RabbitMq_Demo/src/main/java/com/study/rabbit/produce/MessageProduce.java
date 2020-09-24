package com.study.rabbit.produce;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.study.rabbit.config.RabbitmqCfg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 消息生产者
 */
@Component
public class MessageProduce {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitmqCfg rabbitmqCfg;

    public void sendMessage() {
        while (true){
            for (int i = 0; i < 100; i++) {
                rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.PASS", "{\"gcbh\":\""+UUID.randomUUID().toString()+"\",\"sbxh\":\"010001051205025202\",\"jgsjStr\":\"2020-05-10 14:10:00.520\",\"hphm\":\"皖A"+ RandomUtil.randomNumbers(5) +"\",\"hpzl\":\"02\",\"hpys\":\"2\",\"cwkc\":0.0,\"clys\":\"4\",\"cllx\":\"3\",\"sd\":0.0,\"cdbh\":1,\"fx\":\"1\",\"sjlx\":null,\"wfdm\":\"0\",\"tpurl\":\"http://10.20.10.9:10020/2020-09-21/51860300000032004/02_51860300000032004_渝B561Z1_20200921171038359_13523_02_080_090.jpg\",\"tpurl1\":\"\",\"tpurl2\":\"\",\"platePosition\":null,\"csbh\":null,\"sjly\":\"\",\"lsh\":\"703d0d6c05ed428c85fa3d2abf1da7da\",\"dataList\":null,\"jgsj\":\"2020-05-10 14:10:00.520\",\"hjsj\":\"2020-05-10 14:10:00.520\",\"scsj\":null,\"ffsj\":\"2020-05-10 14:10:00.520\",\"rksj\":null,\"jrsj\":\"2020-05-10 14:10:00.520\"}\n");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
