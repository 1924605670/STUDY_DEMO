package com.study.admin.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Jobs {

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void job1() throws InterruptedException {
        log.info("执行job-{}", DateUtil.now());
        Thread.sleep(20000);
    }

}
