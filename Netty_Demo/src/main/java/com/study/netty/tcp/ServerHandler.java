package com.study.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<MessagePojo> {

    static final EventExecutorGroup eventExecutorGroup = new DefaultEventExecutorGroup(16);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePojo msg) throws Exception {
        log.info("handler 线程 {}", Thread.currentThread().getName());

        // 异步执行耗时任务
        // 方式一：普通添加任务 执行的线程和执行handler的线程是同一个线程，加入多个任务之后还是会阻塞的
//        ctx.channel().eventLoop().execute(() -> {
//            log.info("异步方法 线程 {}",Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(5000);
//                        log.info("hello ni好，我是异步执行任务！！！！！！！");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );

        // 异步定时任务执行
//        ctx.channel().eventLoop().schedule(() -> {
//            log.info("执行定时任务！！！！！！！F");
//        }, 10, TimeUnit.SECONDS);

        // 方式二：通过在handler中添加线程池执行耗时异步操作( *************特别推荐**************）
        eventExecutorGroup.submit(() -> {
            try {
                Thread.sleep(10000);
                log.info("异步执行方法线程 {}", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 方式三：通过在 pipeline 中配置context线程池 ，代码见 TcpServerInit ( *************同样推荐**************)

        log.info("收到客户端消息：{}", msg.getContent());
//        MessagePojo messagePojo = new MessagePojo();
//        messagePojo.setContent(UUID.randomUUID().toString());
        ctx.writeAndFlush(UUID.randomUUID().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("异常信息：{}", cause.getMessage());
    }
}
