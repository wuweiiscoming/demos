package org.wigo.demo.mydog.server.operation;

import java.util.Map;

/**
 * @author wuwei31
 * @since 2021/5/22
 */
public abstract class AbstractMapOperation extends AbstractOperation{

    protected final Map<String, Object> map;

    public AbstractMapOperation(Map<String, Object> map) {
        this.map = map;
    }
}
