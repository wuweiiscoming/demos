package org.wigo.demo.mydog.server.command;

import lombok.Data;
import org.wigo.demo.mydog.server.error.BaseError;

/**
 * @author wuwei31
 * @since 2021/5/21
 */
@Data
public class CommandContext {

    private String opt;

    private String[] args;

    private boolean finished;

    private String result;

    private BaseError errMsg;

}
