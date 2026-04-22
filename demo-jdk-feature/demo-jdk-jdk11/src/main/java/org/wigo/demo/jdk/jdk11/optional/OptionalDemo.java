package org.wigo.demo.jdk.jdk11.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * JDK 9~11 Optional 增强 API 示例
 *
 * 面试要点：
 * JDK 9 新增：
 * 1. ifPresentOrElse() - 有值执行一个动作，无值执行另一个
 * 2. or() - 无值时返回另一个 Optional
 * 3. stream() - 将 Optional 转为 Stream（空 Optional 返回空 Stream）
 *
 * JDK 11 新增：
 * 4. isEmpty() - 判断是否为空（与 isPresent 相反）
 */
public class OptionalDemo {

    public static void main(String[] args) {
        // ifPresentOrElse() - JDK 9
        System.out.println("=== ifPresentOrElse() ===");
        Optional<String> opt1 = Optional.of("hello");
        Optional<String> opt2 = Optional.empty();

        opt1.ifPresentOrElse(
            v -> System.out.println("有值: " + v),
            () -> System.out.println("无值")
        );

        opt2.ifPresentOrElse(
            v -> System.out.println("有值: " + v),
            () -> System.out.println("无值")
        );

        // or() - JDK 9
        System.out.println("\n=== or() ===");
        Optional<String> emptyOpt = Optional.empty();
        Optional<String> fallback = emptyOpt.or(() -> Optional.of("默认值"));
        System.out.println("or() 结果: " + fallback);

        // stream() - JDK 9，用于 Stream.flatMap
        System.out.println("\n=== stream() ===");
        Stream.of(
            Optional.of("a"),
            Optional.empty(),
            Optional.of("b")
        )
        .flatMap(Optional::stream)  // 空 Optional 变成空 Stream，自动过滤
        .forEach(System.out::println);

        // isEmpty() - JDK 11
        System.out.println("\n=== isEmpty() ===");
        System.out.println("Optional.empty().isEmpty(): " + Optional.empty().isEmpty());
        System.out.println("Optional.of(1).isEmpty(): " + Optional.of(1).isEmpty());
        System.out.println("Optional.empty().isPresent(): " + Optional.empty().isPresent());

        // 实际应用场景
        System.out.println("\n=== 实际应用 ===");
        String result = findUserById(1)
            .or(() -> findUserById(2))
            .or(() -> Optional.of("Guest"))
            .orElse("Unknown");
        System.out.println("用户: " + result);

        // 传统方式 vs Optional 方式
        System.out.println("\n=== 传统 vs Optional ===");
        String userId = null;
        // 传统方式
        String name1 = userId != null ? userId : "default";
        // Optional 方式
        String name2 = Optional.ofNullable(userId).orElse("default");
        System.out.println("传统: " + name1);
        System.out.println("Optional: " + name2);
    }

    static Optional<String> findUserById(int id) {
        if (id == 1) return Optional.empty();
        if (id == 2) return Optional.of("User-" + id);
        return Optional.empty();
    }
}