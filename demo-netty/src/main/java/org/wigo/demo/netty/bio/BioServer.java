package org.wigo.demo.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wuwei
 * @since 2021/4/11
 */
public class BioServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket  =new ServerSocket(8080);

        Socket accept = serverSocket.accept();

        byte[] bytes = new byte[1024];
        accept.getInputStream().read(bytes);

        System.out.println("收到："+new String(bytes));

        System.out.println("结束");
    }
}
