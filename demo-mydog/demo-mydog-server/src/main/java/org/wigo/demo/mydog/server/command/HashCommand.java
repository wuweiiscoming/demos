package org.wigo.demo.mydog.server.command;

import java.util.HashMap;
import java.util.Map;

import static org.wigo.demo.mydog.server.command.CommandConstant.OK;

/**
 * @author wuwei31
 * @since 2021/5/22
 */
public class HashCommand extends AbstractDictCommand {

    public HashCommand(AbstractCommand next, Map<String, Object> map) {
        super(next, map);
    }

    public void hget(CommandContext context) {
        String[] args = context.getArgs();
        Map<String, String> subMap = (Map<String, String>) map.get(args[0]);
        if (subMap != null) {
            String value = subMap.get(args[1]);
            if (value != null) {
                context.setResult(value);
                return;
            }
        }
        context.setResult("(nil)");
    }

    public void hset(CommandContext context) {
        String[] args = context.getArgs();
        HashMap<String, String> subMap = (HashMap) map.get(args[0]);

        boolean created = subMap == null;
        if (created) {
            subMap = new HashMap<>();
        }

        subMap.put(args[1], args[2]);

        if (created) {
            map.put(args[0], subMap);
        }
        context.setResult(OK);
    }

    @Override
    public boolean matches(String opt) {
        return "hget".equals(opt) || "hset".equals(opt);
    }

    @Override
    public void handle(CommandContext context) {
        switch (context.getOpt()) {
            case "hget":
                hget(context);
                break;
            case "hset":
                hset(context);
                break;
            default:
        }
        context.setFinished(true);
    }
}
