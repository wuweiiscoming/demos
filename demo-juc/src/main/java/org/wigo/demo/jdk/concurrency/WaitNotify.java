package org.wigo.demo.jdk.concurrency;

import lombok.SneakyThrows;

public class WaitNotify {

    static volatile boolean isOn = true;

    final static Object lock = new Object();

    static class Wait implements Runnable {

        long mills;

        public Wait(long mills) {
            this.mills = mills;
        }

        @SneakyThrows
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            long future = now + mills;
            long remaining = mills;
            synchronized (lock) {
                while (isOn && remaining > 0) {
                    System.out.println("等待");
                    lock.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                System.out.println("等待结束" + (System.currentTimeMillis() - now));
            }

        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                isOn = false;
                System.out.println("通知");
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        new Thread(new Wait(1000)).start();
        Thread.sleep(2000);
        new Thread(new Notify()).start();
    }
}
