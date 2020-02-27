package com.study.netty.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class TcpServerInit extends ChannelInitializer<SocketChannel> {
    static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 客户端的编码器和服务端的解码器需要对应
        // 客户端的解码器和服务端的编码器需要对应
//        pipeline.addLast(new PojoToByteEndocer());
        pipeline.addLast(new ByteToPojoDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());
        // 异步执行耗时方法三
        // pipeline.addLast(group,new ServerHandler());
    }
}
