package com.study.sync.controller;

import com.study.sync.service.InterfaceSync;
import com.study.sync.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author chengzhihua
 * @Date 2020/2/13 9:45
 */
@RestController
@RequestMapping("/sync")
@Slf4j
public class SyncController {

    private static Long count = 0L;

    @Autowired
    private SyncService syncService;
    @Autowired
    private InterfaceSync interfaceSync;

    @GetMapping("/hello")
    public String sync() throws InterruptedException {
      log.info("hello 请求！！！{}",++count);
//        for (int i = 0; i < 8; i++) {
////            syncService.sync();
//            interfaceSync.testSync();
//        }
        List list = Arrays.asList(1,2,3,4,5,6,7,8);
        list.stream().forEach(x -> {
            log.info("{}",x);
            interfaceSync.testSync();

        });
        return "hello sync";
    }

}
