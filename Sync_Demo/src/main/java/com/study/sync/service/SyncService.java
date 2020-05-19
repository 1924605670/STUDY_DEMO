package com.study.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author chengzhihua
 * @Date 2020/2/13 9:56
 */
@Service
@Slf4j
public class SyncService {

    @Async
    public void sync() throws InterruptedException {
        log.info("执行sync异步方法");
        Thread.sleep(3000);
        log.info("当前线程--{}", Thread.currentThread().getName());
    }

}
