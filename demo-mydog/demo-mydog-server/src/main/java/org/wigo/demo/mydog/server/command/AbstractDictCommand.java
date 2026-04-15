package org.wigo.demo.mydog.server.command;

import java.util.Map;

/**
 * @author wuwei31
 * @since 2021/5/21
 */
public abstract class AbstractDictCommand extends AbstractCommand {

    protected final Map<String, Object> map;

    public AbstractDictCommand(AbstractCommand next, Map<String, Object> map) {
        super(next);
        this.map = map;
    }

}
