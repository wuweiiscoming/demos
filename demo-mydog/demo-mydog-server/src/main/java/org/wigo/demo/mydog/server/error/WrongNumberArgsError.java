package org.wigo.demo.mydog.server.error;

/**
 * @author wuwei31
 * @since 2021/5/22
 */
public class WrongNumberArgsError extends BaseError {

    public WrongNumberArgsError(String opt, String[] args) {
        super(opt, args);
    }

    @Override
    public String toString() {
        return "(error) ERR wrong number of arguments for '" + opt + "' command";
    }
}
