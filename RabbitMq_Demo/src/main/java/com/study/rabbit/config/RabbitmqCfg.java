package com.study.rabbit.config;

import lombok.Data;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RabbitMQ服务端配置
 * @author zhihua cheng
 */
@Configuration
@Data
@Primary
public class RabbitmqCfg extends RabbitProperties {
    /**队列名称*/
    @Value("${queue.spring.rabbitmq.queues.name}")
    private String queuesName;
    @Value("${queue.spring.rabbitmq.ttl}")
    private long ttl;
    /**交换名称*/
    @Value("${queue.spring.rabbitmq.exchange.name}")
    private String exchangeName;
    /**队列绑定交换使用的RoutingKey*/
    @Value("${queue.spring.rabbitmq.routingKey}")
    private String routingKey;
    /**
     * 定义连接工厂
     * @return
     */
    @Bean("connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(super.getHost());
        connectionFactory.setPort(super.getPort());
        connectionFactory.setUsername(super.getUsername());
        connectionFactory.setPassword(super.getPassword());
        connectionFactory.setVirtualHost("/");
        connectionFactory.setRequestedHeartBeat(20);
        connectionFactory.setPublisherReturns(true);
        //如果要进行消息回调，则这里必须要设置为true
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setChannelCacheSize(50);
        return connectionFactory;
    }
}
