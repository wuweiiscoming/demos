package org.wigo.demo.jdk.jdk21.switchpattern;

/**
 * JDK 21 Switch Pattern Matching（switch 模式匹配）示例
 *
 * Switch 支持模式匹配，可以匹配类型并解构。
 *
 * 面试要点：
 * 1. switch 表达式支持类型模式匹配
 * 2. 使用 case Type t -> 语法
 * 3. 支持卫式条件（guard）使用 when
 * 4. null 检查更简洁（case null ->）
 */
public class SwitchPatternDemo {

    sealed interface Shape permits Circle, Rectangle, Triangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double width, double height) implements Shape {}
    record Triangle(double base, double height) implements Shape {}

    public static void main(String[] args) {
        // 类型模式匹配
        Shape[] shapes = { new Circle(5), new Rectangle(4, 6), new Triangle(3, 4) };

        for (Shape shape : shapes) {
            double area = calculateArea(shape);
            System.out.println(shape + " area = " + area);
        }

        // 卫式条件（guard）
        System.out.println("\n=== 卫式条件 ===");
        Object[] values = { 10, -5, 100, 0, "hello", 3.14 };

        for (Object value : values) {
            System.out.println(classifyNumber(value));
        }

        // null 处理
        System.out.println("\n=== null 处理 ===");
        String str = null;
        String result = processString(str);
        System.out.println("Result: " + result);

        str = "hello";
        result = processString(str);
        System.out.println("Result: " + result);
    }

    // switch 类型模式匹配
    static double calculateArea(Shape shape) {
        return switch (shape) {
            case Circle(double r) -> Math.PI * r * r;
            case Rectangle(double w, double h) -> w * h;
            case Triangle(double b, double h) -> 0.5 * b * h;
        };
    }

    // 卫式条件
    static String classifyNumber(Object obj) {
        return switch (obj) {
            case Integer i when i > 0 && i < 50 -> "Small positive: " + i;
            case Integer i when i >= 50 -> "Large positive: " + i;
            case Integer i when i < 0 -> "Negative: " + i;
            case Integer i -> "Zero or edge case: " + i;
            case Double d -> "Double: " + d;
            case String s -> "String: " + s;
            case null -> "null value";
            default -> "Unknown type";
        };
    }

    // null 处理
    static String processString(String str) {
        return switch (str) {
            case null -> "Input is null";
            case "" -> "Empty string";
            case String s when s.length() > 5 -> "Long string: " + s;
            case String s -> "Short string: " + s;
        };
    }
}