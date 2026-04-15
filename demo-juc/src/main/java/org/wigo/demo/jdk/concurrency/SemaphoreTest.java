package org.wigo.demo.jdk.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(3);

    private static final int THREAD_COUNT = 10;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);


    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("save data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
