package org.wigo.demo.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * 算子
 * 限定输入输出类型一致的Function
 * @author wuwei
 * @since 2020/12/26 3:10 下午
 */
public class Operators {

    @Test
    public void unaryOperator(){
        UnaryOperator<Integer> increment = (i) -> i + 1;
        System.out.println(increment.andThen(increment).apply(1));
    }

    @Test
    public void binaryOperator(){
        BinaryOperator<String> longer = (s1,s2) -> s1.length() > s2.length() ? s1: s2;
        System.out.println(longer.apply("hello", "java"));
    }

}
