package org.wigo.demo.netty.chatroom;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author wuwei31
 * @since 2021/1/29 9:45
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private final static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext context, String s) throws Exception {
        for (Channel channel : channels) {
            if (channel == context.channel()) {
                channel.writeAndFlush("我:" + s);
            } else {
                channel.writeAndFlush(context.channel().remoteAddress() + ":" + s);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.writeAndFlush(ctx.channel().remoteAddress() + "连接");
        System.out.println(ctx.channel().remoteAddress() + "连接");
        channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx);
        channels.writeAndFlush(ctx.channel().remoteAddress() + "断开");
        System.out.println(ctx.channel().remoteAddress() + "断开");
    }
}
