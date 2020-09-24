package com.study.rabbit.lister;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.study.rabbit.beans.DsTfcpassAllEntity;
import com.study.rabbit.repo.PassAllRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-06-29 10:15
 * @Description
 **/
//@Component
@Slf4j
public class Listern {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    @Autowired
    private PassAllRepo passAllRepo;

    @RabbitListener(
            // 绑定队列，如果生命的queue，exchange，routekey不存在，会自动创建
            bindings =
            @QueueBinding(
                    // value:队列名；   exclusive：非独占，true，会上锁；    durable:持久化；     autoDelete:自动删除
                    value = @Queue(value = "${queue.spring.rabbitmq.queues.name}",
                            exclusive = "false",
                            durable = "true",
                            autoDelete = "false",
                            arguments = {
                                    // 当消息队列由于消息过期，或者超过最长数据，消息将进入死亡队列  设置死亡队列exchange （DLE）
                                    @Argument(name = "x-dead-letter-exchange", value = "${queue.spring.rabbitmq.exchange.name.d}"),
                                    // 设置死亡队列routingkey （DLK）
                                    @Argument(name = "x-dead-letter-routing-key", value = "${queue.spring.rabbitmq.routingKey.d}")
                                    // 设置队列消息过期时间 （TTL）
                                    ,@Argument(name = "x-message-ttl", value = "${queue.spring.rabbitmq.ttl}", type = "java.lang.Long")
                            }),
                    // 绑定交换,指定交换名称 type:主题模式(TOPIC) durable:持久化 autoDelete:非自动删除
                    exchange = @Exchange(value = "${queue.spring.rabbitmq.exchange.name}", type = ExchangeTypes.TOPIC, durable = "true", autoDelete = "false"),
                    key = "${queue.spring.rabbitmq.routingKey}"

            )
    )
    public void messageHandle(Channel channel, Message message) throws IOException {
//        channel.basicQos(0,300,false);
        handleMessage(channel, message);
    }


    //死亡队列监听处理
    @RabbitListener(
            //绑定队列，如果声明的Queue、Exchange、RouteKey不存在，将会自动创建
            bindings = @QueueBinding(
                    //绑定队列，指定队列名称,exclusive:非独占 durable:持久化 autoDelete:非自动删除
                    value = @Queue(value = "${queue.spring.rabbitmq.queues.name.d}", exclusive = "false", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "${queue.spring.rabbitmq.exchange.name.d}", type = ExchangeTypes.TOPIC, durable = "true", autoDelete = "false"),
                    //交换与队列绑定的keys
                    key = "${queue.spring.rabbitmq.routingKey.d}"
            )
    )
    public void deadMessageHandle(Channel channel, Message message) throws IOException {
//        channel.basicQos(0,300,false);
        handleMessage(channel, message);
    }

    private void handleMessage(Channel channel, Message message) throws IOException {
        try {
            String msg = new String(message.getBody(), CharsetUtil.CHARSET_UTF_8);
            DsTfcpassAllEntity ds = gson.fromJson(msg, DsTfcpassAllEntity.class);
            passAllRepo.save(ds);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 手动 ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
