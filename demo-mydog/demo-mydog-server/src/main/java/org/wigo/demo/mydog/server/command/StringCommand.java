package org.wigo.demo.mydog.server.command;

import java.util.Map;

import static org.wigo.demo.mydog.server.command.CommandConstant.OK;

/**
 * @author wuwei31
 * @since 2021/5/21
 */
public class StringCommand extends AbstractDictCommand {

    public StringCommand(AbstractCommand next, Map<String, Object> map) {
        super(next, map);
    }

    private void get(CommandContext context) {
        String[] args = context.getArgs();
        String value = (String) map.getOrDefault(args[0], "(nil)");
        context.setResult(value);
    }

    private void set(CommandContext context) {
        String[] args = context.getArgs();
        map.put(args[0], args[1]);
        context.setResult(OK);
    }

    @Override
    public boolean matches(String opt) {
        return "get".equals(opt) || "set".equals(opt);
    }

    @Override
    public void handle(CommandContext context) {
        switch (context.getOpt()) {
            case "get":
                get(context);
                break;
            case "set":
                set(context);
                break;
            default:
        }
        context.setFinished(true);
    }
}
