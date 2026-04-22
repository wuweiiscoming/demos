package org.wigo.demo.jdk.jdk17.patternmatching;

/**
 * JDK 17 instanceof 模式匹配示例
 *
 * 模式匹配简化了类型检查和类型转换。
 *
 * 面试要点：
 * 1. instanceof 同时完成类型检查和类型转换
 * 2. 模式变量只在 true 分支有效
 * 3. 避免冗余的类型转换代码
 */
public class PatternMatchingDemo {

    public static void main(String[] args) {
        // 传统方式
        Object obj = "Hello, World!";
        if (obj instanceof String) {
            String str = (String) obj;  // 需要显式转换
            System.out.println("传统方式: " + str.toUpperCase());
        }

        // 模式匹配方式
        if (obj instanceof String str) {  // 同时完成检查和转换
            System.out.println("模式匹配: " + str.toUpperCase());
        }

        // 模式变量作用域
        Object value = 42;
        if (value instanceof Integer num && num > 0) {
            System.out.println("正整数: " + num);
        }

        // 实际应用：处理不同类型
        System.out.println("\n=== 处理不同类型 ===");
        System.out.println(format(123));
        System.out.println(format(3.14));
        System.out.println(format("test"));
        System.out.println(format(true));
    }

    // 使用模式匹配处理不同类型
    static String format(Object obj) {
        if (obj instanceof Integer i) {
            return "Integer: " + i;
        } else if (obj instanceof Double d) {
            return "Double: " + d;
        } else if (obj instanceof String s) {
            return "String: " + s;
        } else if (obj instanceof Boolean b) {
            return "Boolean: " + b;
        } else {
            return "Unknown type";
        }
    }

    }