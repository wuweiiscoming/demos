package org.wigo.demo.jdk.jdk7blew.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wuwei31
 * @since 2021/4/26
 */
public class IntPair extends Pair<Integer> {

    public IntPair(Integer integer) {
        super(integer);
    }

    public static void main(String[] args) {
        Class<IntPair> clazz = IntPair.class;
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            // 可能有多个泛型类型
            Type[] types = pt.getActualTypeArguments();
            // 取第一个泛型类型
            Type firstType = types[0];
            Class<?> typeClass = (Class<?>) firstType;
            // Integer
            System.out.println(typeClass.getName());
        }
    }
}
