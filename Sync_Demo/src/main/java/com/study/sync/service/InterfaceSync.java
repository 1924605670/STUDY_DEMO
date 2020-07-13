package com.study.sync.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-10 16:53
 * @Description
 **/
@Component
public interface InterfaceSync {

    @Async
    public void testSync() ;
}
