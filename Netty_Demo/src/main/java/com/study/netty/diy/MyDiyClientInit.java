package com.study.netty.diy;

import com.study.netty.client.MyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyDiyClientInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyByteToLong());
        pipeline.addLast(new MyLongToByte());
        pipeline.addLast(new DiyClientHandle());
    }
}
