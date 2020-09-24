package com.study.sync.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-10 16:53
 * @Description
 **/
@Component
@Slf4j
public class InterfaceSyncImpl implements InterfaceSync {
    @Autowired
    private SyncService syncService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void testSync() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("over {}", Thread.currentThread().getName());
    }

    @Override
    public void save() {
        for (int i = 0; i < 1000000; i++) {
            syncService.saveSync();
        }
    }

    @Override
    public void save2() {
        String sql = "INSERT INTO `ds_tfcpass20200825`(`GCBH`, `sbxh`, `GCSJ`, `HPHM`, `hpzl`, `hpys`, `cwkc`, `clys`, `cllx`, `sd`, `cdbh`, `fx`, `sjlx`, `wfdm`, `tpurl`, `tpurl1`, `tpurl2`, `platePosition`, `csbh`, `acceptTime`, `analysisTime`, `sendToMQTime`, `SendToMQState`, `getForMQTime`, `storageTime`, `sendToLDTime`, `sendToRminfTime`, `sendToTmriTime`, `sendToLTHTime`, `saveToLTHTime`) VALUES ('" + UUID.randomUUID().toString().substring(4) + "', '1231', '2020-08-26 10:53:24.000', '111', '1', '1', 1, '1', '1', 1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '2020-08-26 10:53:38.000', '2020-08-26 10:53:41.000', '2020-08-26 10:53:44.000', 1, '2020-08-26 10:53:47.000', '" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.sss") + "', '2020-08-26 10:53:51.000', '2020-08-26 10:53:54.000', '2020-08-26 10:53:59.000', '2020-08-26 10:54:02.000', '2020-08-26 10:54:05.000');";
        log.info("Thread : {}", Thread.currentThread());
        jdbcTemplate.execute(sql);
    }


}


