package org.wigo.demo.mydog.server.error;

import lombok.Data;

/**
 * @author wuwei31
 * @since 2021/5/22
 */
@Data
public abstract class BaseError {

    protected String opt;

    protected String[] args;

    public BaseError(String opt, String[] args) {
        this.opt = opt;
        this.args = args;
    }
}
