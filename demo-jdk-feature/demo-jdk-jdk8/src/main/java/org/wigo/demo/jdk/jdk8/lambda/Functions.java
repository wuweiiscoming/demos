package org.wigo.demo.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author wuwei
 * @since 2020/12/26 3:03 下午
 */
public class Functions {

    @Test
    public void function() {
        Function<String, Integer> lengthOf = String::length;
        Function<Integer, Integer> square = (i) -> i * i;
        Integer result = lengthOf.andThen(square).apply("test");
        System.out.println(result);
    }

    @Test
    public void biFunction(){
        BiFunction<Person,Person,String> names = (p1,p2) -> p1.name + "," + p2.name;
        Person person1 = new Person("小明", 15);
        Person person2 = new Person("小刚", 17);
        System.out.println(names.apply(person1, person2));
    }

}
