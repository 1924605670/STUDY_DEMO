package com.study.sync.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author chengzhihua
 * @Date 2020/2/13 10:00
 * 线程池配置
 */
@Configuration
@Slf4j
public class TreadPoolConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        log.info("线程池数量：{}",Runtime.getRuntime().availableProcessors());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        System.out.println(executor.getPoolSize());
        //核心线程池数量，方法: 返回可用处理器的Java虚拟机的数量。
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        //最大线程数量
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 5);
        //线程池的队列容量
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors() * 2);
        //线程名称的前缀
        executor.setThreadNamePrefix("this-excutor-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务中异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        log.error("异步任务执行异常");
        // TODO Auto-generated method stub
        return new SimpleAsyncUncaughtExceptionHandler();
    }


}
