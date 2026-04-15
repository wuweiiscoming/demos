package org.wigo.demo.mydog.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
public class SplitPacketDecoder extends ByteToMessageDecoder {

    int length;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (length == 0 && in.readableBytes() >= Integer.BYTES) {
            length = in.readInt();
        }
        if (in.readableBytes() < length) {
            return;
        }
        byte[] content = new byte[length];
        in.readBytes(content);

        out.add(content);

        length = 0;
    }

}
