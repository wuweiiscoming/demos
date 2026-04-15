package org.wigo.demo.jdk.concurrency;

import java.util.Stack;

/**
 * 10个线程倒序开始，顺序结束
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        Stack<Thread> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new WaitPrevious(previous));
            stack.add(thread);
            previous = thread;
        }
        // 倒序执行
        Thread thread;
        while (!stack.empty()) {
            thread = stack.pop();
            thread.start();
        }
    }

    static class WaitPrevious implements Runnable {
        private Thread previous;

        public WaitPrevious(Thread before) {
            this.previous = before;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                previous.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }
}
