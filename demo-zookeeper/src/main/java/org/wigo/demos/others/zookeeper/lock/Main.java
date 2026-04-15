package org.wigo.demos.others.zookeeper.lock;

import lombok.SneakyThrows;

public class Main {

//    static Lock lock = new ReentrantLock();
    static ZkLock2 lock = new ZkLock2();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new UserThread(),"user1");
        Thread thread2 = new Thread(new UserThread(),"user2");
        thread1.start();
        thread2.start();
    }

    static class UserThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            new Order().createOrder();
            lock.lock();
            boolean success = new Stock().reduceStock();
            lock.unlock();
            if (success) {
                new Pay().pay();
            }
        }
    }
}
