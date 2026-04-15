package org.wigo.demo.jdk.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new BarrierAction());


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        Thread.sleep(100);
        cyclicBarrier.await();
        System.out.println(2);
    }

    /**
     * 最后一个到达栅栏的线程执行的动作
     */
    static class BarrierAction implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
