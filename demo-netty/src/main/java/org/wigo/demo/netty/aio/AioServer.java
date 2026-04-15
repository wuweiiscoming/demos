package org.wigo.demo.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author wuwei31
 * @since 2021/4/12
 */
public class AioServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9000));

        assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {

                System.out.println("连接建立");
                assc.accept(attachment,this);

                ByteBuffer byteBuffer  =ByteBuffer.allocate(1024);

                socketChannel.read(byteBuffer,byteBuffer,new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println("收到："+new String(attachment.array()));
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });

                System.out.println();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });

        Thread.sleep(Integer.MAX_VALUE);

    }


}
