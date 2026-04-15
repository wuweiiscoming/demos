package org.wigo.demo.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
public class SplitPacketEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getBytes(CharsetUtil.UTF_8).length);
        out.writeBytes(msg.getBytes(CharsetUtil.UTF_8));
    }
}
