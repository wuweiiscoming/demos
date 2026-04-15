package org.wigo.demo.netty.reconnect;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
public class ReconnectHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("重连...");
        ctx.connect(ctx.channel().remoteAddress());
    }
}
