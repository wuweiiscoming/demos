package org.wigo.demo.mydog.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public class RequestHandler extends SimpleChannelInboundHandler<String[]> {

    private final ICommandHandler commandHandler;

    public RequestHandler(ICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String[] command) throws Exception {
        System.out.println("收到命令："+ Arrays.toString(command));

        String result = commandHandler.handle(command);

        ctx.channel().writeAndFlush(result.getBytes());
    }

}
