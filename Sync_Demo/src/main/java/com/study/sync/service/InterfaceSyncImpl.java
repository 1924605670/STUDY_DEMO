package com.study.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-10 16:53
 * @Description
 **/
@Component
@Slf4j
public class InterfaceSyncImpl implements InterfaceSync {

    @Override
    public void testSync(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("over {}", Thread.currentThread().getName());
    }
}
