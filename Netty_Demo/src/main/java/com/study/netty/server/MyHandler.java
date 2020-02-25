package com.study.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author chengzhihua
 * @Date 2020/2/21 14:59
 */
@Slf4j
public class MyHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 管理所有的channel ，需要手动添加 channel ，但是会在度拿开链接时自动移除
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        log.info("服务端收到msg:{}", s);
        channelGroup.add(ctx.channel());
        channelGroup.writeAndFlush(ctx.channel().remoteAddress() + ": " + s);
    }


    /**
     * 有连接活跃连接上方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("sss");
    }

    /**
     * 不活跃的时候方法执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * 断开链接的时候操作方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }
}
