package org.wigo.demo.java.agent.jar.cli;

import lombok.SneakyThrows;
import org.wigo.demo.java.agent.jar.command.AbstractCommand;
import org.wigo.demo.java.agent.jar.command.MyBatisRefreshCommand;
import org.wigo.demo.java.agent.jar.command.VmToolCommand;
import org.wigo.demo.java.agent.jar.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wuwei31
 * @since 2021/7/8
 */
public class CommandMetadata {

    private static final Map<String, Class<? extends AbstractCommand>> classMap = new HashMap<>();

    private static final Map<String, CommandDescriptor> descriptorMap = new HashMap<>();

    public CommandMetadata() {
        init();
    }

    private static void initClassMap() {
        classMap.put("vmtool", VmToolCommand.class);
        classMap.put("mybatis", MyBatisRefreshCommand.class);
    }

    private static void init() {
        // 初始化命令
        initClassMap();
        // 初始化描述
        classMap.forEach((commandName, commandClass) -> {
            Set<Method> requiredSet = new HashSet<>();
            Map<String, Method> shortNameMap = new HashMap<>();
            Map<String, Method> longNameMap = new HashMap<>();

            for (Field field : commandClass.getDeclaredFields()) {
                Option annotation = field.getAnnotation(Option.class);
                if (annotation == null) {
                    continue;
                }
                // 获取set方法
                Method method = findSetMethod(field, commandClass);

                String shortName = annotation.shortName();
                if (StringUtils.isNotEmpty(shortName)) {
                    shortNameMap.put(shortName, method);
                }

                String longName = annotation.longName();
                if (StringUtils.isNotEmpty(longName)) {
                    longNameMap.put(longName, method);
                }

                if (annotation.required()) {
                    requiredSet.add(method);
                }
            }

            CommandDescriptor descriptor = new CommandDescriptor();
            descriptor.setShortNameMap(shortNameMap);
            descriptor.setLongNameMap(longNameMap);
            descriptor.setRequired(requiredSet);
            descriptorMap.put(commandName, descriptor);
        });
    }

    @SneakyThrows
    private static Method findSetMethod(Field field, Class<? extends AbstractCommand> clazz) {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
        return propertyDescriptor.getWriteMethod();
    }

    @SneakyThrows
    public AbstractCommand newCommandInstance(String commandName) {
        Class<? extends AbstractCommand> clazz = classMap.get(commandName);
        if (clazz == null) {
            throw new IllegalArgumentException("command class of " + commandName + " does not exists");
        }

        return clazz.getConstructor().newInstance();
    }

    public CommandDescriptor getDescriptor(String commandName) {
        return descriptorMap.get(commandName);
    }
}
