package org.wigo.demo.jdk.jdk25.scopedvalues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 【预览特性默认关闭，需要开启开关，否则编译报错】
 *
 * JDK 25 Scoped Values（作用域值）示例
 *
 * Scoped Values 是 ThreadLocal 的现代替代方案，专为虚拟线程设计。
 * 解决了 ThreadLocal 在虚拟线程中的问题：不可变、作用域明确、自动清理。
 *
 * 面试要点：
 * 1. Scoped Values 是不可变的，避免 ThreadLocal 的可变性问题
 * 2. 作用域明确，绑定只在特定范围内有效
 * 3. 自动继承到子线程，无需手动传递
 * 4. 性能优于 ThreadLocal，适合虚拟线程大规模使用
 * 5. 使用 ScopedValue.where() 绑定，ScopedValue.get() 获取
 */
public class ScopedValuesDemo {

    // 定义 Scoped Value（类似于 ThreadLocal 的定义）
    static final ScopedValue<String> USER_ID = ScopedValue.newInstance();
    static final ScopedValue<Integer> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        System.out.println("=== Scoped Value 基本使用 ===");

        // 绑定 Scoped Value 并在作用域内执行
        ScopedValue.where(USER_ID, "user-123")
                .where(REQUEST_ID, 1001)
                .run(() -> {
                    processRequest();
                });

        // 作用域外无法获取值（抛出异常）
        System.out.println("\n=== 作用域外访问 ===");
        tryGetOutsideScope();

        System.out.println("\n=== 多层作用域 ===");
        // Scoped Value 可以被重新绑定（在嵌套作用域中）
        ScopedValue.where(USER_ID, "outer-user").run(() -> {
            System.out.println("Outer scope: " + USER_ID.get());

            ScopedValue.where(USER_ID, "inner-user").run(() -> {
                System.out.println("Inner scope: " + USER_ID.get());
            });

            System.out.println("Back to outer scope: " + USER_ID.get());
        });

        System.out.println("\n=== 与虚拟线程配合 ===");
        // Scoped Values 自动继承到子虚拟线程
        ScopedValue.where(USER_ID, "parent-user").run(() -> {
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                executor.submit(() -> {
                    System.out.println("Child virtual thread inherited: " + USER_ID.get());
                }).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("\n=== ThreadLocal vs ScopedValue 对比 ===");
        // ThreadLocal 的问题演示截屏2026-04-22 15.39.15
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("threadlocal-value");

        // ThreadLocal 可以随时修改（不安全）
        threadLocal.set("modified-value");
        System.out.println("ThreadLocal (可变): " + threadLocal.get());

        // ScopedValue 不可变
        ScopedValue.where(USER_ID, "scoped-value").run(() -> {
            System.out.println("ScopedValue (不可变): " + USER_ID.get());
            // USER_ID.set("modified") 不存在此方法！
        });
    }

    static void processRequest() {
        System.out.println("Processing request...");
        System.out.println("User ID: " + USER_ID.get());
        System.out.println("Request ID: " + REQUEST_ID.get());

        // 在方法内部调用其他方法，Scoped Value 自动传递
        validateUser();
        saveData();
    }

    static void validateUser() {
        System.out.println("Validating user: " + USER_ID.get());
    }

    static void saveData() {
        System.out.println("Saving data for request: " + REQUEST_ID.get());
    }

    static void tryGetOutsideScope() {
        try {
            // 作用域外调用 get() 会抛出 NoSuchElementException
            System.out.println(USER_ID.get());
        } catch (Exception e) {
            System.out.println("Outside scope: NoSuchElementException thrown (expected)");
        }

        // 使用 isBound() 检查是否绑定
        System.out.println("USER_ID isBound outside scope: " + USER_ID.isBound());

        // 使用 orElse 提供默认值
        System.out.println("USER_ID orElse: " + USER_ID.orElse("default-user"));
    }
}