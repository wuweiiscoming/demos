package org.wigo.demo.java.agent.jar.util;

/**
 * @author wuwei31
 * @since 2021/7/8
 */
public class StringUtils {


    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}
