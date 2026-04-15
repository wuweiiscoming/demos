package org.wigo.demo.mydog.server.operation.hash;

import org.wigo.demo.mydog.server.command.CommandContext;
import org.wigo.demo.mydog.server.operation.AbstractMapOperation;

import java.util.HashMap;
import java.util.Map;

import static org.wigo.demo.mydog.server.command.CommandConstant.OK;

/**
 * @author wuwei31
 * @since 2021/5/22
 */
public class HsetOperation extends AbstractMapOperation {

    private static final String HSET = "hset";

    public HsetOperation(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getName() {
        return HSET;
    }

    @Override
    protected void validate(CommandContext context) {

    }

    @Override
    public void exec(CommandContext context) {
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
}
