package org.wigo.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author wuwei
 * @since 2021/2/6 上午11:05
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SelectableChannel channel = selectionKey.channel();

                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {

                    SocketChannel socketChannel = ((SocketChannel) selectionKey.channel());

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);

                    System.out.println(new String(byteBuffer.array()));
                }
                iterator.remove();
            }
        }

    }

}
