package org.wigo.demo.netty.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @author wuwei
 * @since 2021/2/1 下午10:53
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                // 使用NioSocketChannel作为客户端的通道实现
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });

        System.out.println("netty client starting ...");

        ChannelFuture future = bootstrap.connect("localhost", 8888).sync();

        Channel channel = future.channel();

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){
            // 如果没有加编解码器，则只能发送ByteBuf类型数据
            channel.writeAndFlush(Unpooled.copiedBuffer(scanner.nextLine(), CharsetUtil.UTF_8));
        }

    }
}
