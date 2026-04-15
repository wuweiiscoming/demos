package org.wigo.demo.mydog.server.operation.hash;

import org.wigo.demo.mydog.server.command.CommandContext;
import org.wigo.demo.mydog.server.operation.AbstractMapOperation;

import java.util.Map;

/**
 * @author wuwei31
 * @since 2021/5/22
 */
public class HgetOperation extends AbstractMapOperation {

    private static final String HGET = "hget";

    public HgetOperation(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getName() {
        return HGET;
    }

    @Override
    protected void validate(CommandContext context) {

    }

    @Override
    public void exec(CommandContext context) {
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
}
