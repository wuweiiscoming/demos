package org.wigo.demo.mydog.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.wigo.demo.mydog.netty.splitpacket.SplitPacketDecoder;
import org.wigo.demo.mydog.netty.splitpacket.SplitPacketEncoder;

/**
 * @author wuwei31
 * @since 2021/5/20
 */
public class ClientConnection implements AutoCloseable{

    private final String host;
    private final int port;

    public ClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    Channel channel;

    public void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new SplitPacketDecoder())
                                .addLast(new ResponseHandler())

                                .addLast(new SplitPacketEncoder())
                                .addLast(new RespProtocolEncoder())
                        ;
                    }
                })
                .connect(host, port).sync();


        System.out.println("client start");

        channel = channelFuture.channel();
    }

    public void write(String command) {
        channel.writeAndFlush(command);
    }

    @Override
    public void close() {
        channel.close();
    }
}
