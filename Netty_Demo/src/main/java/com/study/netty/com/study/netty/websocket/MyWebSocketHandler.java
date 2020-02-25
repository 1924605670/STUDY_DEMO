package com.study.netty.com.study.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        Channel channel = channelHandlerContext.channel();
        log.info("收到浏览器消息：{}", textWebSocketFrame.text());
        // 输出类型需要跟handler的泛型一致
        channel.writeAndFlush(new TextWebSocketFrame("服务器说你是傻逼！！！！"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("有链接发生");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("链接断开");
    }
}
