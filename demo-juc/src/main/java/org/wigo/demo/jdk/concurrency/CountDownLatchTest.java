package org.wigo.demo.jdk.concurrency;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        case2();
    }

    /**
     * 等待所有线程执行结束再执行主线程
     * CountDownLatch参数为线程数
     * 此时
     * await再主线程中
     * countDown在线程run方法的结束位置
     *
     * @throws InterruptedException
     */
    private static void case1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread runner1 = new Thread(new Runner(countDownLatch));
        Thread runner2 = new Thread(new Runner(countDownLatch));
        Thread runner3 = new Thread(new Runner(countDownLatch));
        ThreadLocalTimer timer = new ThreadLocalTimer();
        timer.start();
        runner1.start();
        runner2.start();
        runner3.start();
        countDownLatch.await();
        timer.end();
    }

    /**
     * 让所有线程就绪后一起执行
     * CountDownLatch参数为1
     * 此时
     * await在线程run方法的开始位置
     * countDown在主线程中
     *
     * @throws InterruptedException
     */
    private static void case2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread runner1 = new Thread(new RunTogether(countDownLatch));
        Thread runner2 = new Thread(new RunTogether(countDownLatch));
        Thread runner3 = new Thread(new RunTogether(countDownLatch));
        ThreadLocalTimer timer = new ThreadLocalTimer();
        timer.start();
        runner1.start();
        Thread.sleep(500);
        runner2.start();
        Thread.sleep(500);
        runner3.start();
        countDownLatch.countDown();
        timer.end();
    }


    @AllArgsConstructor
    static class Runner implements Runnable {

        private final CountDownLatch countDownLatch;

        @Override
        public void run() {
            System.out.println("开始" + Thread.currentThread().getName());
            try {
                Random r = new Random();
                long time = r.nextInt(3000);
                Thread.sleep(time);
                System.out.println("睡眠:" + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    @AllArgsConstructor
    static class RunTogether implements Runnable {

        private final CountDownLatch countDownLatch;

        @SneakyThrows
        @Override
        public void run() {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + "开始执行,当前时间" + System.currentTimeMillis());
            // 业务逻辑...
        }
    }
}
