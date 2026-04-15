package org.wigo.demo.mydog.server;


import org.wigo.demo.mydog.netty.NettyServer;

import java.io.IOException;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public class MyDogServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        NettyServer server = new NettyServer(6379, new DefaultCommandHandler());
        server.start();
    }

}
