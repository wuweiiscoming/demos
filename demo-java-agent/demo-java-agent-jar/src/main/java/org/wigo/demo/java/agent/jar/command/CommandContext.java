package org.wigo.demo.java.agent.jar.command;

import lombok.SneakyThrows;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wuwei31
 * @since 2021/7/9
 */
public class CommandContext {

    private Socket socket;

    public CommandContext(Socket socket) {
        this.socket = socket;
    }

    @SneakyThrows
    public void reply(String result) {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write((result + "\n").getBytes());
        outputStream.flush();
    }
}
