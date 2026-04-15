package org.wigo.demo.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author wuwei
 * @since 2020/12/26 2:20 下午
 */
public class Consumers {

    @Test
    public void consumer(){
        Consumer<String> consumer = (s) -> System.out.println("法克鱿，"+s);
        consumer.accept("尼玛");
    }

    @Test
    public void biConsumer(){
        BiConsumer<String,Integer> biConsumer = (s,i)-> System.out.println(s+"今年"+i+"岁");
        biConsumer.accept("李雷",18);
    }
}
