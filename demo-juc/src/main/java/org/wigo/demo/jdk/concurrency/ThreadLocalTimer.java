package org.wigo.demo.jdk.concurrency;

public class ThreadLocalTimer {

    final ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    public void start() {
        long start = System.currentTimeMillis();
        threadLocal.set(start);
        System.out.println("TIME START:" + start);
    }

    public void end() {
        long end = System.currentTimeMillis();
        long start = threadLocal.get();
        System.out.println("TIME END:" + end + ",USED:" + (end - start));
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTimer timer = new ThreadLocalTimer();
        timer.start();
        Thread.sleep(1000);
        timer.end();
    }


}
