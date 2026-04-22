package org.wigo.demo.jdk.jdk21.virtualthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;

/**
 * JDK 21 虚拟线程（Virtual Threads）示例
 *
 * 虚拟线程是轻量级线程，由 JVM 管理，而非操作系统。
 * 可以创建数百万个虚拟线程而不消耗太多资源。
 *
 * 面试要点：
 * 1. 虚拟线程适合 I/O 密集型任务，不适合 CPU 密集型任务
 * 2. 创建成本低，可以大规模创建
 * 3. 使用 Executors.newVirtualThreadPerTaskExecutor() 创建
 * 4. Thread.ofVirtual() 创建单个虚拟线程
 * 5. 虚拟线程会自动"挂载"到平台线程（carrier thread）执行
 * 6. 遇到阻塞操作（I/O、sleep等）时会自动"卸载"，释放 carrier thread
 */
public class VirtualThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 创建单个虚拟线程 ===");
        Thread virtualThread = Thread.ofVirtual().name("my-virtual-thread").start(() -> {
            System.out.println("Hello from virtual thread: " + Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        virtualThread.join();

        System.out.println("\n=== 虚拟线程执行器 ===");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10; i++) {
                executor.submit(() -> {
                    System.out.println("Task running on: " + Thread.currentThread());
                    try {
                        // 模拟 I/O 操作
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Task completed";
                });
            }
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }

        System.out.println("\n=== 大量虚拟线程演示 ===");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100_000; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(100);  // 模拟 I/O 阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
            long end = System.currentTimeMillis();
            System.out.println("100,000 个虚拟线程完成任务耗时: " + (end - start) + "ms");
        }

        System.out.println("\n=== 平台线程 vs 虚拟线程对比 ===");
        System.out.println("平台线程数量限制: " + Runtime.getRuntime().availableProcessors() * 256);

        // 检查是否是虚拟线程
        Thread thread = Thread.currentThread();
        System.out.println("当前线程是否是虚拟线程: " + thread.isVirtual());
    }
}