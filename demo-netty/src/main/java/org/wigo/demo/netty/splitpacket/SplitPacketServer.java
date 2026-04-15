package org.wigo.demo.netty.splitpacket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author wuwei
 * @since 2021/2/6 上午10:04
 */
public class SplitPacketServer {

    public static void main(String[] args) throws InterruptedException {
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
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new MessageHandler())
                        ;
                    }
                })
                .bind(8888).sync();

        System.out.println("server started");

        channelFuture.channel().closeFuture().sync();
    }

}
