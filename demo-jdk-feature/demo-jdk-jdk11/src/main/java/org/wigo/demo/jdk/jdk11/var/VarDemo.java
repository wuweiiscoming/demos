package org.wigo.demo.jdk.jdk11.var;

/**
 * JDK 10 局部变量类型推断（var）示例
 *
 * var 只能用于局部变量，编译器自动推断类型。
 *
 * 面试要点：
 * 1. var 只能用于局部变量（方法内、代码块内）
 * 2. 不能用于成员变量、方法参数、返回类型
 * 3. 必须有初始化值，编译器才能推断类型
 * 4. 推断后类型不可变，不是动态类型
 * 5. var 不是关键字，只是保留类型名
 * 6. 适合复杂泛型类型简化，不适合简单类型（降低可读性）
 */
public class VarDemo {

    // 成员变量不能使用 var
    // var name = "test";  // 编译错误

    public static void main(String[] args) {
        // 基本使用
        var name = "Alice";        // 推断为 String
        var age = 25;              // 推断为 int
        var price = 99.99;         // 推断为 double

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        // 复杂泛型类型 - var 的最佳使用场景
        var list = java.util.List.of("a", "b", "c");  // List<String>
        var map = new java.util.HashMap<String, java.util.List<Integer>>();  // 简化复杂声明

        // 集合遍历
        for (var item : list) {
            System.out.println(item);
        }

        // Stream 中使用
        list.stream()
            .filter(var s -> s.startsWith("a"))  // Lambda 参数不能用 var（JDK 11 可以）
            .forEach(System.out::println);

        // JDK 11: Lambda 参数可以使用 var
        list.stream()
            .filter((var s) -> s.startsWith("a"))
            .forEach(System.out::println);

        // try-with-resources
        try (var input = new java.util.Scanner(System.in)) {
            // ...
        }

        // var 推断后类型不可变
        var num = 10;     // int
        // num = "hello"; // 编译错误，类型不匹配

        // 不能使用 var 的场景
        // var x;          // 错误：没有初始化
        // var x = null;   // 错误：无法推断类型

        // 方法返回类型不能用 var
        // public var getValue() { return 1; }  // 错误

        // 方法参数不能用 var
        // public void test(var param) {}  // 错误

        // 使用建议
        System.out.println("\n=== 使用建议 ===");
        System.out.println("适合：复杂泛型声明如 Map<String, List<Set<Integer>>>");
        System.out.println("不适合：简单类型如 var i = 10 (降低可读性)");
    }
}