package org.wigo.demo.jdk.jdk11.stream;

import java.util.stream.Stream;
import java.util.stream.IntStream;

/**
 * JDK 9 Stream API 增强示例
 *
 * 面试要点：
 * 1. takeWhile() - 取出连续符合条件的元素（遇到不满足即停止）
 * 2. dropWhile() - 跳过连续符合条件的元素（遇到不满足即停止）
 * 3. iterate() - 增加终止条件参数
 * 4. ofNullable() - 允许创建包含 null 的 Stream（null 变空 Stream）
 */
public class StreamDemo {

    public static void main(String[] args) {
        // takeWhile() - 取出连续满足条件的元素
        System.out.println("=== takeWhile() ===");
        Stream.of(1, 2, 3, 4, 5, 3, 2, 1)
            .takeWhile(n -> n < 4)
            .forEach(System.out::print);  // 输出: 1 2 3 (遇到4停止)
        System.out.println();

        // dropWhile() - 跳过连续满足条件的元素
        System.out.println("\n=== dropWhile() ===");
        Stream.of(1, 2, 3, 4, 5, 3, 2, 1)
            .dropWhile(n -> n < 4)
            .forEach(System.out::print);  // 输出: 4 5 3 2 1
        System.out.println();

        // 对比 filter()
        System.out.println("\n=== takeWhile vs filter ===");
        System.out.println("takeWhile: 只取第一段连续满足的");
        System.out.println("filter: 取所有满足的");

        // iterate() - 增加终止条件
        System.out.println("\n=== iterate() ===");
        // 传统 iterate (无限流，需要 limit)
        Stream.iterate(0, n -> n + 1)
            .limit(5)
            .forEach(System.out::print);
        System.out.println();

        // JDK 9 iterate (有限流，有终止条件)
        IntStream.iterate(0, n -> n < 10, n -> n + 2)
            .forEach(System.out::print);  // 输出: 0 2 4 6 8
        System.out.println();

        // 实际应用：生成斐波那契数列
        System.out.println("\n斐波那契数列前10项:");
        Stream.iterate(new int[]{0, 1}, f -> f[0] < 100, f -> new int[]{f[1], f[0] + f[1]})
            .map(f -> f[0])
            .forEach(System.out::print);
        System.out.println();

        // ofNullable() - 处理可能为 null 的值
        System.out.println("\n=== ofNullable() ===");
        String nullable = null;
        String value = "hello";

        // 传统方式处理 null
        Stream<String> oldWay = nullable != null ? Stream.of(nullable) : Stream.empty();

        // JDK 9 ofNullable
        Stream<String> newWay = Stream.ofNullable(nullable);  // 返回空 Stream

        System.out.println("ofNullable(null) count: " + Stream.ofNullable(null).count());  // 0
        System.out.println("ofNullable(value) count: " + Stream.ofNullable(value).count()); // 1

        // 实际应用：集合中可能有 null
        System.out.println("\n过滤集合中的 null:");
        Stream.of("a", null, "b", null, "c")
            .flatMap(Stream::ofNullable)  // 自动过滤 null
            .forEach(System.out::print);
        System.out.println();
    }
}