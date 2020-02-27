package com.study.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PojoToByteEndocer extends MessageToByteEncoder<MessagePojo> {


    @Override
    protected void encode(ChannelHandlerContext ctx, MessagePojo msg, ByteBuf out) throws Exception {
        int len = msg.getContent().getBytes().length;
        out.writeInt(len);
        out.writeBytes(msg.getContent().getBytes());
    }
}
