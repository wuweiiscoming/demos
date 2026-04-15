package org.wigo.demo.java.agent.jar.command;

import lombok.Getter;
import lombok.Setter;
import org.wigo.demo.java.agent.jar.cli.Option;
import org.wigo.demo.java.agent.jar.ognl.OgnlHandler;

/**
 * 格式
 * vmtool [--classLoaderClass {classLoader}] [--className {className}] --express {ognl}
 * <p>
 * classLoader和className非不填
 * <p>
 * e.g.
 * vmtool --className org.springframework.context.ApplicationContext --express #instances
 * <p>
 * #instances 代表{className}类在jvm中实例列表
 *
 * @author wuwei31
 * @since 2021/7/7
 */
public class VmToolCommand extends AbstractCommand {

    private OgnlHandler ognl = new OgnlHandler();

    @Option(shortName = "c", longName = "classLoaderName")
    @Setter
    @Getter
    private String classLoaderName;

    @Option(longName = "className")
    @Setter
    @Getter
    private String className;

    @Option(longName = "express")
    @Setter
    @Getter
    private String express;

    @Override
    public String process() {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        }
        Object[] instances = vmTool.getInstances(clazz);
        return ognl.handle(instances, express);
    }


}
