package com.study.netty.diy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiyClientHandle extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {
      log.info("客户端回复：{}",aLong);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("链接成功！！");
        ctx.writeAndFlush(1256L);
    }
}
