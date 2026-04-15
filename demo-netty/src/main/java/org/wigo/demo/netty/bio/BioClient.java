package org.wigo.demo.netty.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wuwei
 * @since 2021/4/11
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("你好".getBytes());

        outputStream.flush();

    }
}
