package com.study.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {

    public void run() {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder", new StringDecoder());
                            socketChannel.pipeline().addLast("encoder", new StringEncoder());
                            socketChannel.pipeline().addLast("myClientHandler", new MyClientHandler());
                        }
                    });


            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();

            log.info("channel 连接状态：{}", channelFuture.isSuccess());

//            for (int i = 0; i < 100; i++) {
//                Thread.sleep(2000);
//                channelFuture.channel().writeAndFlush(i+"hello");
//            }
            channelFuture.channel().writeAndFlush("Head:* Version:V2.0 Cmd:80 length:710 lsh:71537c8502a44d341b91 sbbh:PC-YJ190600011 gcsj:20200414110655172 hphm:皖AFR466 hpys:- hpzl:-1 cwkc:0 csys:- cllx:0 clsd:96 cdbh:1 wfdm: clxh:40 tpurl:ftp://36.7.87.130:1212/111111.jpg tpurl1: tpur2: jrsj:2020-04-14 11:06:28 Tail:#");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        new NettyClient().run();
    }

}
