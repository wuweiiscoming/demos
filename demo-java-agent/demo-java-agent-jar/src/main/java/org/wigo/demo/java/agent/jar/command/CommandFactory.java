package org.wigo.demo.java.agent.jar.command;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wigo.demo.java.agent.jar.cli.CommandMetadata;
import org.wigo.demo.java.agent.jar.cli.CommandDescriptor;
import org.wigo.demo.java.agent.jar.cli.OptionEntity;
import org.wigo.demo.java.agent.jar.cli.OptionResolver;
import org.wigo.demo.java.agent.jar.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wuwei31
 * @since 2021/7/9
 */
public class CommandFactory {

    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    private static final CommandMetadata commandMetadata = new CommandMetadata();

    @SneakyThrows
    public static AbstractCommand create(String line) {
        String[] arr = line.split(" ");
        String commandName = arr[0];
        if (StringUtils.isEmpty(commandName)) {
            throw new IllegalArgumentException("command is not valid" + line);
        }

        CommandDescriptor descriptor = commandMetadata.getDescriptor(commandName);
        if (descriptor == null) {
            throw new IllegalArgumentException("command name:" + commandName + " does not exists");
        }

        AbstractCommand command = commandMetadata.newCommandInstance(commandName);

        // 去除第一个元素命令名和最后一个元素ongl表达式
        String[] optionArr = new String[arr.length - 1];
        System.arraycopy(arr, 1, optionArr, 0, arr.length - 1);
        // 参数数组解析为选项
        List<OptionEntity> options = OptionResolver.resolve(optionArr);
        for (OptionEntity option : options) {
            Method method = null;
            if (option.getShortName() != null) {
                method = descriptor.getShortNameMap().get(option.getShortName());
                if (method == null) {
                    throw new IllegalArgumentException("option:" + option.getShortName() + " is not supported");
                }
            }
            if (option.getLongName() != null) {
                method = descriptor.getLongNameMap().get(option.getLongName());
                if (method == null) {
                    throw new IllegalArgumentException("option:" + option.getLongName() + " is not supported");
                }
            }
            method.invoke(command, option.getValue());
        }

        logger.debug("command created,line:{},command:{}", line, command);
        return command;
    }

}
