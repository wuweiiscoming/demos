package org.wigo.demo.java.agent.jar.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wigo.demo.java.agent.jar.command.AbstractCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuwei31
 * @since 2021/7/8
 */
public class OptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCommand.class);

    public static List<OptionEntity> resolve(String[] args) {
        List<OptionEntity> options = new ArrayList<>();

        boolean isName = true;
        String name;
        OptionEntity option = null;
        for (int i = 0; i < args.length; i++) {
            if (isName) {
                name = args[i];
                option = new OptionEntity();
                if (name.startsWith("--")) {
                    option.setLongName(name.substring(2));
                } else if (name.startsWith("-")) {
                    option.setShortName(name.substring(1));
                } else {
                    throw new IllegalArgumentException("args can not be resolved to options,args:" + Arrays.toString(args));
                }
                isName = false;
            } else {
                option.setValue(args[i]);
                options.add(option);
                isName = true;
            }
        }
        logger.debug("args resolved success,args:{},options:{}", args, options);
        return options;
    }
}
