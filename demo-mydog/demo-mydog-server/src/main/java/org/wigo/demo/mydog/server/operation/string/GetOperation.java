package org.wigo.demo.mydog.server.operation.string;

import org.wigo.demo.mydog.server.command.CommandContext;
import org.wigo.demo.mydog.server.error.WrongNumberArgsError;
import org.wigo.demo.mydog.server.operation.AbstractMapOperation;

import java.util.Map;

/**
 * @author wuwei31
 * @since 2021/5/21
 */
public class GetOperation extends AbstractMapOperation {

    private static final String GET = "get";

    private static final int ARG_LEN = 1;

    public GetOperation(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getName() {
        return GET;
    }

    @Override
    protected void validate(CommandContext context) {
        String[] args = context.getArgs();
        if (args.length != ARG_LEN) {
            context.setErrMsg(new WrongNumberArgsError(context.getOpt(), args));
            context.setFinished(true);
        }
    }

    @Override
    public void exec(CommandContext context) {
        String[] args = context.getArgs();
        String value = (String) map.getOrDefault(args[0], "(nil)");
        context.setResult(value);
    }
}
