package com.study.rabbitmq.produce.produce;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chengzhihua
 * @description
 * @date 2020/12/15
 */
@Component
@Slf4j
public class Produce {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${produce.once.count:5}")
    private int countOnce;

    @Scheduled(initialDelay = 1000,fixedDelayString = "${produce.fixed:20000}")
    public void produce(){
        log.info("生成mq测试数据");
        for (int i = 0; i < countOnce; i++) {
            String pass = "{\"gcbh\":\""+RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER,20)+"\",\"sbxh\":\"632500000000011112\",\"jgsjStr\":\""+ DateUtil.now() +".233\",\"hphm\":\"皖A"+ RandomUtil.randomNumbers(5) +"\",\"hpzl\":\"02\",\"hpys\":\"2\",\"cwkc\":0.0,\"clys\":\"4\",\"cllx\":\"3\",\"sd\":0.0,\"cdbh\":1,\"fx\":\"1\",\"sjlx\":null,\"wfdm\":\"0\",\"tpurl\":\"http://10.20.10.5:50510/p/MTU4Nzk3MzIyMTU4MV84MzI4NDA4ZF8zMTEwNDAwMDAwMF9PVEg=.jpg\",\"tpurl1\":\"\",\"tpurl2\":\"\",\"platePosition\":null,\"csbh\":null,\"sjly\":\"\",\"lsh\":\""+RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER,20)+"\",\"dataList\":null,\"jgsj\":\""+ DateUtil.now() +".233\",\"hjsj\":\""+DateUtil.now()+".233\",\"scsj\":null,\"ffsj\":\""+DateUtil.now()+".234\",\"rksj\":null,\"jrsj\":\""+DateUtil.now()+".206\"}";
            String vio = "{\"gcbh\":\""+RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER,20)+"\",\"sbxh\":\"512000000000120001\",\"jgsjStr\":\""+ DateUtil.now() +".233\",\"hphm\":\"皖A"+ RandomUtil.randomNumbers(5) +"\",\"hpzl\":\"02\",\"hpys\":\"2\",\"cwkc\":0.0,\"clys\":\"4\",\"cllx\":\"3\",\"sd\":0.0,\"cdbh\":1,\"fx\":\"1\",\"sjlx\":null,\"wfdm\":\"123456\",\"tpurl\":\"http://10.20.10.5:50510/p/MTU4Nzk3MzIyMTU4MV84MzI4NDA4ZF8zMTEwNDAwMDAwMF9PVEg=.jpg\",\"tpurl1\":\"\",\"tpurl2\":\"\",\"platePosition\":null,\"csbh\":null,\"sjly\":\"\",\"lsh\":\""+RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER,20)+"\",\"dataList\":null,\"jgsj\":\""+ DateUtil.now() +".233\",\"hjsj\":\""+DateUtil.now()+".233\",\"scsj\":null,\"ffsj\":\""+DateUtil.now()+".234\",\"rksj\":null,\"jrsj\":\""+DateUtil.now()+".206\"}";
            String gps = "{\"lsh\":\""+RandomUtil.randomNumbers(10)+"\",\"sbbh\":\"8F0C1ED4B1E0C819\",\"hpzl\":\"02\",\"hphm\":\"皖A"+RandomUtil.randomNumbers(5)+"\",\"sbsj\":\""+ DateUtil.now() +"\",\"jd\":\"120.651712\",\"wd\":\"27.9743648\",\"sd\":\"1\",\"fx\":\"1\",\"sjly\":\"\"}";
            // 过车
//            rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS","BCHT.TFCPASS.HESSIAN.PASS",pass);
            // 违法
            rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS","BCHT.TFCPASS.HESSIAN.VIOLATION",vio);
            // GPS
//            rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS","BCHT.TFCPASS.HESSIAN.GPS",vio);
        }

    }

}
