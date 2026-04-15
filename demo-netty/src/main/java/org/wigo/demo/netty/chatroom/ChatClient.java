package org.wigo.demo.netty.chatroom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author wuwei31
 * @since 2021/1/29 9:44
 */
public class ChatClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = bootstrap

                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new ChatClientHandler())
                        ;
                    }
                })
                .connect("localhost", 8888).sync();

        System.out.println("进入聊天室");

        Scanner scanner = new Scanner(System.in);

        Channel channel = channelFuture.channel();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < 200; i++) {
                channel.writeAndFlush(line);
            }
        }

    }
}
