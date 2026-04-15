package org.wigo.demo.mydog.client;

import org.wigo.demo.mydog.client.exception.CliParseException;

/**
 * @author wuwei31
 * @since 2021/6/1
 */
public class GetCommand extends CliCommand {

    private static final String CMD = "get";

    private String optionDesc = "key";

    private String[] args;

    public GetCommand(String[] args) {
        this.args = args;
    }

    @Override
    protected void validate() {
        if(args.length != 1){
            throw new CliParseException(getUsage());
        }
    }

    @Override
    public void exec() {

    }

    @Override
    public String getCmd() {
        return CMD;
    }

    @Override
    public String getOptionDesc() {
        return optionDesc;
    }
}
