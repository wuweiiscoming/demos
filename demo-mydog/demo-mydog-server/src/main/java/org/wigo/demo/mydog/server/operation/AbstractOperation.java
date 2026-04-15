package org.wigo.demo.mydog.server.operation;

import org.wigo.demo.mydog.server.command.CommandContext;

/**
 * @author wuwei31
 * @since 2021/5/21
 */
public abstract class AbstractOperation {

    public abstract String getName();

    protected abstract void validate(CommandContext context);

    public abstract void exec(CommandContext context);

}
