package com.study.thrift.client;

import com.study.thrift.client.conf.ThriftServerConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author chengzhihua
 * @description
 * @date 2021/1/5
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    /**
     * 向Spring注册一个Bean对象
     * initMethod = "start"  表示会执行名为start的方法
     * start方法执行之后，就会阻塞接受客户端的请求
     *
     * @return
     */
    @Bean(initMethod = "start")
    public ThriftServerConf init() {
        ThriftServerConf thriftServer = new ThriftServerConf();
        return thriftServer;
    }
}
