package com.study.netty.diy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyDiyInitServer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel serverSocketChannel) throws Exception {
        ChannelPipeline pipeline = serverSocketChannel.pipeline();
        pipeline.addLast("encoder",new MyLongToByte());
        pipeline.addLast("decoder",new MyByteToLong());
        pipeline.addLast(new MyServerHandler());
    }
}
