package com.study.sync.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @Author chengzhihua
 * @Date 2020/2/13 9:56
 */
@Service
@Slf4j
public class SyncService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Async
    public void sync() throws InterruptedException {
        log.info("执行sync异步方法");
        Thread.sleep(3000);
        log.info("当前线程--{}", Thread.currentThread().getName());
    }

    @Async
    public void saveSync() {
        String sql = "INSERT INTO `ds_tfcpass20200825`(`GCBH`, `sbxh`, `GCSJ`, `HPHM`, `hpzl`, `hpys`, `cwkc`, `clys`, `cllx`, `sd`, `cdbh`, `fx`, `sjlx`, `wfdm`, `tpurl`, `tpurl1`, `tpurl2`, `platePosition`, `csbh`, `acceptTime`, `analysisTime`, `sendToMQTime`, `SendToMQState`, `getForMQTime`, `storageTime`, `sendToLDTime`, `sendToRminfTime`, `sendToTmriTime`, `sendToLTHTime`, `saveToLTHTime`) VALUES ('" + UUID.randomUUID().toString().substring(4) + "', '1231', '2020-08-26 10:53:24.000', '111', '1', '1', 1, '1', '1', 1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '2020-08-26 10:53:38.000', '2020-08-26 10:53:41.000', '2020-08-26 10:53:44.000', 1, '2020-08-26 10:53:47.000', '" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.sss") + "', '2020-08-26 10:53:51.000', '2020-08-26 10:53:54.000', '2020-08-26 10:53:59.000', '2020-08-26 10:54:02.000', '2020-08-26 10:54:05.000');";
        log.info("Thread : {}", Thread.currentThread());
        jdbcTemplate.execute(sql);
    }

}
