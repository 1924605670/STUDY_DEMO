package com.study.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ByteToPojoDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int len = in.readInt();
        MessagePojo messagePojo = new MessagePojo();
        messagePojo.setLen(len);
        byte[] countBytes = new byte[len];
        in.readBytes(countBytes);
        messagePojo.setContent(new String(countBytes));
        out.add(messagePojo);
    }
}
