package org.wigo.demo.jdk.concurrency.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS (Compare-And-Swap) 示例
 *
 * CAS 是一种无锁并发控制机制，核心思想：
 * 比较当前值与期望值是否相等，如果相等则更新为新值，否则不做操作
 *
 * Java 中的原子类底层都使用 CAS 实现（如 AtomicInteger、AtomicReference）
 *
 * @author wuwei
 */
public class CASTest {

    public static void main(String[] args) throws Exception {
        // 取消注释运行不同示例
//        basicCASExample();
//        simulateCASExample();
//        abaProblemDemo();
//        abaSolutionDemo();
        casCounterExample();
    }

    // ==================== 基础 CAS 使用 ====================

    /**
     * AtomicInteger 的 compareAndSet 方法演示
     * 这是 CAS 操作的直接使用方式
     */
    public static void basicCASExample() {
        System.out.println("=== 基础 CAS 使用 ===");

        AtomicInteger atomicInt = new AtomicInteger(100);

        // CAS 操作：如果当前值等于期望值，则更新
        boolean success1 = atomicInt.compareAndSet(100, 200);
        System.out.println("第一次 CAS: 期望100, 更新200, 结果=" + success1 + ", 当前值=" + atomicInt.get());

        // 再次尝试，当前值已是200，期望100会失败
        boolean success2 = atomicInt.compareAndSet(100, 300);
        System.out.println("第二次 CAS: 期望100, 更新300, 结果=" + success2 + ", 当前值=" + atomicInt.get());

        // 成功的 CAS
        boolean success3 = atomicInt.compareAndSet(200, 300);
        System.out.println("第三次 CAS: 期望200, 更新300, 结果=" + success3 + ", 当前值=" + atomicInt.get());
    }

    // ==================== 模拟 CAS 实现 ====================

    /**
     * 模拟 CAS 操作的实现原理
     * 实际 JVM 使用 native 方法实现，这里用 Java 模拟
     */
    public static void simulateCASExample() {
        System.out.println("=== 模拟 CAS 实现 ===");

        SimulatedCAS cas = new SimulatedCAS(10);

        // 多线程并发执行 CAS
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    int expected = cas.getValue();
                    // 模拟 CAS: 如果值没变，则更新
                    // 这里可能失败，因为其他线程可能已经修改了值
                    cas.compareAndSwap(expected, expected + 1);
                }
            }, "Thread-" + i).start();
        }

        sleep(2000);
        System.out.println("最终值: " + cas.getValue());
        System.out.println("(由于是模拟实现，不是真正的原子操作，结果可能小于5000)");
    }

    /**
     * 模拟 CAS 的简单实现
     * 注意：这不是真正的原子操作，仅用于演示 CAS 思想
     */
    static class SimulatedCAS {
        private volatile int value;

        public SimulatedCAS(int initialValue) {
            this.value = initialValue;
        }

        public int getValue() {
            return value;
        }

        /**
         * 模拟 CAS 操作
         * 真正的 CAS 是原子操作，这里只是模拟逻辑
         */
        public synchronized boolean compareAndSwap(int expected, int newValue) {
            if (value == expected) {
                value = newValue;
                return true;
            }
            return false;
        }
    }

    // ==================== ABA 问题演示 ====================

    /**
     * CAS 的经典问题：ABA 问题
     *
     * 场景：线程1准备将 A 改为 C，在检查期间值被改为 B 又改回 A
     * 线程1看到值还是 A，以为没变，实际已经变了
     */
    public static void abaProblemDemo() throws InterruptedException {
        System.out.println("=== ABA 问题演示 ===");

        AtomicInteger atomicInt = new AtomicInteger(100);

        // 线程1：准备将 100 改为 200，但先延迟一下
        Thread thread1 = new Thread(() -> {
            sleep(500); // 延迟，让线程2有机会执行
            boolean success = atomicInt.compareAndSet(100, 200);
            System.out.println("线程1 CAS 结果: " + success + ", 当前值=" + atomicInt.get());
            System.out.println("线程1 认为值没变(还是100)，但实际上经历了 100->110->100 的变化");
        });

        // 纸程2：将 100 改为 110，再改回 100（产生ABA问题）
        Thread thread2 = new Thread(() -> {
            atomicInt.compareAndSet(100, 110);
            System.out.println("线程2: 100 -> 110");
            atomicInt.compareAndSet(110, 100);
            System.out.println("线程2: 110 -> 100");
        });

        thread2.start();
        thread1.start();

        thread1.join();
        thread2.join();
    }

    // ==================== ABA 问题解决方案 ====================

    /**
     * 使用 AtomicStampedReference 解决 ABA 问题
     * 通过版本号/戳来标记每次修改，即使值相同但版本不同也会失败
     */
    public static void abaSolutionDemo() throws InterruptedException {
        System.out.println("=== ABA 问题解决方案 (AtomicStampedReference) ===");

        // 初始值100，版本号0
        AtomicStampedReference<Integer> stampedRef = new AtomicStampedReference<>(100, 0);

        // 线程1：准备将 100 改为 200
        Thread thread1 = new Thread(() -> {
            sleep(500);
            int[] stampHolder = new int[1];
            Integer currentValue = stampedRef.get(stampHolder); // 获取值和版本号
            int stamp = stampHolder[0];

            boolean success = stampedRef.compareAndSet(currentValue, 200, stamp, stamp + 1);
            System.out.println("线程1 CAS 结果: " + success + ", 当前值=" + stampedRef.getReference());
            if (!success) {
                System.out.println("线程1 失败原因: 版本号已变化，期望stamp=" + stamp + ", 实际stamp=" + stampedRef.getStamp());
            }
        });

        // 线程2：将 100 改为 110，再改回 100（版本号会变化）
        Thread thread2 = new Thread(() -> {
            int[] stampHolder = new int[1];
            Integer value = stampedRef.get(stampHolder);
            int stamp = stampHolder[0];

            stampedRef.compareAndSet(value, 110, stamp, stamp + 1);
            System.out.println("线程2: 100 -> 110, stamp: " + stamp + " -> " + stampedRef.getStamp());

            value = stampedRef.get(stampHolder);
            stamp = stampHolder[0];
            stampedRef.compareAndSet(value, 100, stamp, stamp + 1);
            System.out.println("线程2: 110 -> 100, stamp: " + stamp + " -> " + stampedRef.getStamp());
        });

        thread2.start();
        thread1.start();

        thread1.join();
        thread2.join();
    }

    // ==================== CAS 实战示例 ====================

    /**
     * 使用 CAS 实现线程安全的计数器
     * 比使用 synchronized 更高效（无锁）
     */
    public static void casCounterExample() throws InterruptedException {
        System.out.println("=== CAS 实现线程安全计数器 ===");

        CASCounter counter = new CASCounter();

        // 10个线程，每个线程增加1000次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            }, "Thread-" + i).start();
        }

        sleep(2000);
        System.out.println("预期结果: 10000");
        System.out.println("实际结果: " + counter.getValue());
    }

    /**
     * 基于 CAS 的无锁计数器
     * 使用自旋+ retries 的方式实现
     */
    static class CASCounter {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            // 自旋 CAS：如果失败则重试
            while (true) {
                int current = count.get();
                int next = current + 1;
                if (count.compareAndSet(current, next)) {
                    break;
                }
                // CAS 失败，自旋重试
            }
        }

        public int getValue() {
            return count.get();
        }
    }

    // ==================== 工具方法 ====================

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}