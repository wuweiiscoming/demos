package org.wigo.demo.mydog.server;

import org.wigo.demo.mydog.netty.ICommandHandler;
import org.wigo.demo.mydog.server.command.AbstractCommand;
import org.wigo.demo.mydog.server.command.CommandContext;
import org.wigo.demo.mydog.server.command.HashCommand;
import org.wigo.demo.mydog.server.command.StringCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public class DefaultCommandHandler implements ICommandHandler {

    private final AbstractCommand chain;

    public DefaultCommandHandler() {
        chain = buildChain();
    }

    @Override
    public String handle(String[] command) {
        String[] args = Arrays.copyOfRange(command, 1, command.length);

        CommandContext context = new CommandContext();
        context.setOpt(command[0]);
        context.setArgs(args);

        chain.handleChain(context);
        return context.getResult();
    }

    private AbstractCommand buildChain() {
        Map<String, Object> map = new HashMap<>();

        HashCommand hash = new HashCommand(
                null,
                map);

        StringCommand string = new StringCommand(
                hash,
                map);

        return string;
    }
}
