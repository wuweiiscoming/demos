package org.wigo.demo.mydog.server.operation.string;

import org.wigo.demo.mydog.server.command.CommandContext;
import org.wigo.demo.mydog.server.error.WrongNumberArgsError;
import org.wigo.demo.mydog.server.operation.AbstractMapOperation;

import java.util.Map;

import static org.wigo.demo.mydog.server.command.CommandConstant.OK;

/**
 * @author wuwei
 * @since 2021/5/22
 */
public class SetOperation extends AbstractMapOperation {

    private static final String SET = "set";

    private static final int ARG_LEN = 2;

    public SetOperation(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getName() {
        return SET;
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
        map.put(args[0], args[1]);
        context.setResult(OK);
    }
}
