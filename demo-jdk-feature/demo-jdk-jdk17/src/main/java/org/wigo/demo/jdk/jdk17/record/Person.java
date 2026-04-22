package org.wigo.demo.jdk.jdk17.record;

/**
 * JDK 17 Record 示例 - Person 类
 */
public record Person(String name, int age) {

    // 紧凑构造函数，用于参数校验
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }

    // 实例方法
    public boolean isAdult() {
        return age >= 18;
    }

    // 静态工厂方法
    public static Person of(String name, int age) {
        return new Person(name, age);
    }

    // 静态方法
    public static Person anonymous(int age) {
        return new Person("Anonymous", age);
    }
}