package org.wigo.demo.jdk.concurrency.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference 示例
 *
 * AtomicReference 对引用类型进行原子操作，CAS 比较的是引用（对象地址），不是对象内容
 *
 * @author wuwei
 */
public class AtomicReferenceTest {

    public static void main(String[] args) throws Exception {
        // 取消注释运行不同示例
        basicCASExample();

        System.out.println();
//        referenceVsContentExample();
//        concurrentUpdateExample();
    }

    /**
     * 基础 CAS 操作：引用比较
     */
    public static void basicCASExample() {
        System.out.println("=== 基础 CAS 操作 ===");

        User user1 = new User("张三", 25);
        AtomicReference<User> atomicRef = new AtomicReference<>(user1);

        System.out.println("初始值: " + atomicRef.get());

        // CAS 成功：期望引用与实际引用相同
        User user2 = new User("李四", 30);
        boolean success1 = atomicRef.compareAndSet(user1, user2);
        System.out.println("CAS(user1 -> user2): " + success1 + ", 结果: " + atomicRef.get());

        // CAS 失败：期望引用与实际引用不同（当前是 user2，期望 user1）
        User user3 = new User("王五", 28);
        boolean success2 = atomicRef.compareAndSet(user1, user3);
        System.out.println("CAS(user1 -> user3): " + success2 + ", 结果: " + atomicRef.get());
    }

    /**
     * 演示：AtomicReference 比较引用，不比较内容
     * 即使两个对象内容相同，引用不同也会失败
     */
    public static void referenceVsContentExample() {
        System.out.println("=== 引用比较 vs 内容比较 ===");

        User original = new User("张三", 25);
        AtomicReference<User> atomicRef = new AtomicReference<>(original);

        // 创建一个内容相同但引用不同的对象
        User sameContent = new User("张三", 25);
        User newUser = new User("李四", 30);

        // CAS 使用引用比较，即使内容相同也会失败
        boolean success = atomicRef.compareAndSet(sameContent, newUser);
        System.out.println("期望对象内容: " + sameContent);
        System.out.println("当前对象内容: " + atomicRef.get());
        System.out.println("内容相同但引用不同，CAS 结果: " + success);
        System.out.println("AtomicReference 比较的是引用(==)，不是对象内容(equals)");
    }

    /**
     * 并发场景：多个线程竞争更新 AtomicReference
     */
    public static void concurrentUpdateExample() throws InterruptedException {
        System.out.println("=== 并发更新示例 ===");

        AtomicReference<User> atomicRef = new AtomicReference<>(new User("初始用户", 0));

        // 10个线程并发更新
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    // 自旋 CAS 更新
                    while (true) {
                        User current = atomicRef.get();
                        User newUser = new User("用户-" + Thread.currentThread().getId(), current.getAge() + 1);
                        if (atomicRef.compareAndSet(current, newUser)) {
                            break;
                        }
                        // CAS 失败，自旋重试
                    }
                }
            }, "Thread-" + i).start();
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("最终结果: " + atomicRef.get());
        System.out.println("age 应该是 1000 (10线程 × 100次)");
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private int age;
    }
}