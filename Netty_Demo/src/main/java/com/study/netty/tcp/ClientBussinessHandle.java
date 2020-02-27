package com.study.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端业务处理handler
 */
@Slf4j
public class ClientBussinessHandle extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("客户端收到消息：{}", msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            MessagePojo pojo = new MessagePojo();
            pojo.setContent("hello world"+i);
            ctx.writeAndFlush(pojo);
        }
    }
}
