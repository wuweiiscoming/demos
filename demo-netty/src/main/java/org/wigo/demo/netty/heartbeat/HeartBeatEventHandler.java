package org.wigo.demo.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
public class HeartBeatEventHandler extends SimpleChannelInboundHandler<String> {

    int readIdleTimes;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event  = ((IdleStateEvent) evt);

        if(event.state() == IdleState.READER_IDLE){
            readIdleTimes ++;

            System.out.println("第"+readIdleTimes+"次读超时");

            if(readIdleTimes > 3){
                System.out.println("关闭连接");
                ctx.channel().close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
