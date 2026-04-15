package org.wigo.demo.mydog.client;

/**
 * @author wuwei31
 * @since 2021/6/1
 */
public abstract class CliCommand {

    public String getUsage() {
        return getCmd() + " " + getOptionDesc();
    }

    protected abstract void validate();

    public abstract void exec();

    protected abstract String getCmd();

    protected abstract String getOptionDesc();

}
