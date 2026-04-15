package org.wigo.demo.netty.splitpacket;

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
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (length == 0 && in.readableBytes() >= Integer.BYTES) {
            length = in.readInt();
        }
        if (in.readableBytes() < length) {
            return;
        }
        System.out.println(length);
        byte[] content = new byte[length];
        in.readBytes(content);

        PacketWrapper wrapper = new PacketWrapper();
        wrapper.setLength(length);
        wrapper.setContent(content);
        out.add(wrapper);

        length = 0;
    }

}
