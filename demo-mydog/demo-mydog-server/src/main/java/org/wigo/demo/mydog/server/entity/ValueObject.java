package org.wigo.demo.mydog.server.entity;

import lombok.Data;

/**
 * @author wuwei31
 * @since 2021/5/23
 */
@Data
public class ValueObject {
    /**
     * 0.string 1.hash 2.set 3.zset
     */
    private int type;

    private Object value;
}
