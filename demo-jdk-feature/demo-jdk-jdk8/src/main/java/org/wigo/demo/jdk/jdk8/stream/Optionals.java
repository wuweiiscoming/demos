package org.wigo.demo.jdk.jdk8.stream;

import org.wigo.demo.jdk.jdk8.lambda.Person;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author wuwei
 * @since 2020/12/26 3:39 下午
 */
public class Optionals {

    @Test
    public void optional() {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println(fullName.isPresent());
        System.out.println(fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(e -> "hello " + e).orElse("hello shadiao"));
    }

    /**
     * 存在则返回
     */
    @Test
    public void case1() {
        Optional<Person> personOptional = Optional.ofNullable(null);
        personOptional.ifPresent(System.out::print);
    }

    /**
     * 存在则返回，无则返回默认值
     */
    @Test
    public void case2() {
        Optional<Person> personOptional = Optional.ofNullable(null);
        Person person = personOptional.orElse(new Person("default", 0));
        System.out.println(person.name);
    }

    /**
     * 存在则返回，无则生成默认
     */
    @Test
    public void case3() {
        Optional<Person> personOptional = Optional.ofNullable(null);
        Person person = personOptional.orElseGet(() -> new Person("default", 0));
        System.out.println(person.age);
    }

    /**
     * 夺命连环null检查
     */
    @Test
    public void case4() {
        Optional<Person> personOptional = Optional.ofNullable(null);
        Optional<Person> personOptional2 = Optional.of(new Person("tom", 1));
        Function<Optional<Person>, String> function = (o) -> o.map(e -> e.name)
                .map(String::toUpperCase)
                .map(e -> "dear " + e)
                .orElse("no one");
        System.out.println(function.apply(personOptional));
        System.out.println(function.apply(personOptional2));
    }
}
