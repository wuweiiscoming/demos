package org.wigo.demo.jdk.concurrency.volatilee;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 *
 * @author wuwei
 * @since 2021/2/28 下午12:02
 */
public class VolatileTest {

    private int count1 = 0;

    // volatile不能保证线程安全
    private volatile int count2 = 0;

    private AtomicInteger count3 = new AtomicInteger();

    private void increaseCount() {
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count1++;
                }
            });
            Thread thread2 = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count2++;
                }
            });
            Thread thread3 = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count3.incrementAndGet();
                }
            });
            thread1.start();
            thread2.start();
            thread3.start();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        test.increaseCount();
        Thread.sleep(2000);
        System.out.println("count1:" + test.count1);
        System.out.println("count2:" + test.count2);
        System.out.println("count3:" + test.count3);
    }

}
