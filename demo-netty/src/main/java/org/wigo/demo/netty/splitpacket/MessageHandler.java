package org.wigo.demo.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author wuwei31
 * @since 2021/5/20
 */
public class MessageHandler extends SimpleChannelInboundHandler<PacketWrapper> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PacketWrapper msg) throws Exception {
        System.out.println(new String(msg.getContent(), CharsetUtil.UTF_8));
    }

}
