package org.wigo.demo.java.agent.jar.command;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author wuwei31
 * @since 2021/7/6
 */
public class CommandServer {

    private static final Logger logger = LoggerFactory.getLogger(CommandServer.class);

    @SneakyThrows
    public void start(int port) {
        ServerSocket serverSocket = new ServerSocket(port);
        logger.info("command server started at {}", port);

        Socket socket = serverSocket.accept();
        logger.info("command socket accepted...");

        Scanner scanner = new Scanner(socket.getInputStream());

        CommandContext context = new CommandContext(socket);

        String line;
        while ((line = scanner.nextLine()) != null) {
            logger.debug("command server receive line:{}", line);
            if ("exit".equals(line)) {
                scanner.close();
                socket.close();
                logger.info("command server closed");
                return;
            } else {
                try {
                    AbstractCommand command = CommandFactory.create(line);
                    String result = command.process();
                    logger.debug("command processed, line:{}, result:{}", line, result);
                    context.reply(result);
                } catch (IllegalArgumentException iae) {
                    logger.error("line is not valid, line:{}", line);
                    context.reply(iae.getMessage());
                } catch (Exception e) {
                    logger.error("command server exception occurred, line:{}", line, e);
                    context.reply(e.toString());
                }
            }
        }

    }

    public static void main(String[] args) {
        new CommandServer().start(8888);
    }

}
