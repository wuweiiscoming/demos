package org.wigo.demo.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * @author wuwei
 * @since 2020/12/26 2:54 下午
 */
public class Predicates {

    @Test
    public void predicate() {
        Predicate<String> startWithHello = (s) -> s.startsWith("hello");
        Predicate<String> lengthGreaterThan10 = (s) -> s.length() > 5;
        System.out.println(startWithHello.and(lengthGreaterThan10).test("hello world"));
    }

    @Test
    public void biPredicate() {
        Person person1 = new Person("小明", 15);
        Person person2 = new Person("小刚", 17);
        BiPredicate<Person, Person> olderThan = (p1, p2) -> p1.age > p2.age;
        System.out.println(olderThan.test(person1, person2));
    }

}
