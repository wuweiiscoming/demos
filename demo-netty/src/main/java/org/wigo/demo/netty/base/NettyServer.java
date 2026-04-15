package org.wigo.demo.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wuwei
 * @since 2021/2/1 下午10:32
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup(2);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                // 使用NioServerSocketChannel作为服务端通道实现
                .channel(NioServerSocketChannel.class)
                // 连接队列大小
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });

        System.out.println("netty org.wigo.mydog.server starting ...");

        // bind是异步操作，sync等待其完毕
        ChannelFuture cf = bootstrap.bind(8888).sync();

        // 监听通道关闭事件，并阻塞等待
        cf.channel().closeFuture().sync();
    }
}
