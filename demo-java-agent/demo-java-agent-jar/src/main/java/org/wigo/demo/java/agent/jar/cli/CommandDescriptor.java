package org.wigo.demo.java.agent.jar.cli;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wuwei31
 * @since 2021/7/8
 */
public class CommandDescriptor {

    @Getter
    @Setter
    private Set<Method> required = new HashSet<>();

    @Getter
    @Setter
    private Map<String, Method> shortNameMap = new HashMap<>();

    @Getter
    @Setter
    private Map<String, Method> longNameMap = new HashMap<>();

}
