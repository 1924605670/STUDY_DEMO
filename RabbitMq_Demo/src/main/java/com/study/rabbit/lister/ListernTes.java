package com.study.rabbit.lister;

import cn.hutool.core.util.CharsetUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.study.rabbit.beans.DsTfcpassAllEntity;
import com.study.rabbit.repo.PassAllRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
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
public class ListernTes {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    @Autowired
    private PassAllRepo passAllRepo;

    @RabbitListener(
            // 绑定队列，如果生命的queue，exchange，routekey不存在，会自动创建
            bindings =
            @QueueBinding(
                    // value:队列名；   exclusive：非独占，true，会上锁；    durable:持久化；     autoDelete:自动删除
                    value = @Queue(value = "111111",
                            exclusive = "false",
                            durable = "true",
                            autoDelete = "false"
                           ),
                    // 绑定交换,指定交换名称 type:主题模式(TOPIC) durable:持久化 autoDelete:非自动删除
                    exchange = @Exchange(value = "BCHT_ITS_DATA", type = ExchangeTypes.TOPIC, durable = "true", autoDelete = "false"),
                    key = "111111"

            )
    )
    public void messageHandle(Channel channel, Message message) throws IOException {
//        channel.basicQos(0,300,false);
        handleMessage(channel, message);
    }




    private void handleMessage(Channel channel, Message message) throws IOException {
        try {
            String msg = new String(message.getBody(), CharsetUtil.CHARSET_UTF_8);
            log.info("msg {}",msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 手动 ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
