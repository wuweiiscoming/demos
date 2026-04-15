package org.wigo.demo.netty.heartbeat;

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
import org.wigo.demo.netty.chatroom.ChatClientHandler;

import java.util.Random;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
public class HeartBeatClient {


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

        System.out.println("已连接服务端");

        Channel channel = channelFuture.channel();

        Random random = new Random();
        while (channel.isActive()) {
            int time = random.nextInt(10);
            System.out.println("延迟" + time + "秒");
            Thread.sleep(time * 1000);
            channel.writeAndFlush("heart beat message");
        }

        System.out.println("断开连接");
    }


}
