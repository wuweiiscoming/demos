package org.wigo.demo.jdk.jdk17.record;

/**
 * JDK 17 Record（记录类）示例
 *
 * Record 是一种不可变数据类，自动生成：
 * - 构造函数
 * - getter 方法
 * - equals、hashCode、toString 方法
 *
 * 面试要点：
 * 1. Record 是不可变的，所有字段默认为 final
 * 2. 适合作为数据载体（DTO）
 * 3. 可以实现接口，可以定义静态方法和实例方法
 * 4. 不能继承其他类（隐式继承 java.lang.Record）
 */
public class RecordDemo {

    interface Printable {
        String print();
    }

    // Record 实现接口
    record Point(int x, int y) implements Printable {
        @Override
        public String print() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Alice", 25);
        System.out.println(person.name());  // 自动生成的 getter
        System.out.println(person.age());
        System.out.println(person);          // 自动生成的 toString
        System.out.println("Is adult: " + person.isAdult());

        // 使用静态工厂方法
        Person anonymous = Person.anonymous(30);
        System.out.println(anonymous);

        // Record 的 equals 和 hashCode
        Person person2 = new Person("Alice", 25);
        System.out.println("person equals person2: " + person.equals(person2));

        // 实现 Record 接口
        Point point = new Point(10, 20);
        System.out.println(point.print());
    }
}