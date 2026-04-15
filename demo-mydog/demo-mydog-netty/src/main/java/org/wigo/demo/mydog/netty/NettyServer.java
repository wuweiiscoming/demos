package org.wigo.demo.mydog.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.wigo.demo.mydog.netty.splitpacket.SplitPacketDecoder;
import org.wigo.demo.mydog.netty.splitpacket.SplitPacketEncoder;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public class NettyServer {

    private final int port;

    private final ICommandHandler commandHandler;

    public NettyServer(int port, ICommandHandler commandHandler) {
        this.port = port;
        this.commandHandler = commandHandler;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup(5);
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 需要按顺序加，否则报错
                        ch.pipeline()
                                .addLast(new SplitPacketDecoder())
                                .addLast(new RespProtocolDecoder())
                                .addLast(new RequestHandler(commandHandler))

                                .addLast(new SplitPacketEncoder())
                        ;
                    }
                })
                .bind(port).sync();

        System.out.println("server started");

        channelFuture.channel().closeFuture().sync();
    }

}
