package org.wigo.demo.mydog.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
public class SplitPacketEncoder extends MessageToByteEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] bytes, ByteBuf out) throws Exception {
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
