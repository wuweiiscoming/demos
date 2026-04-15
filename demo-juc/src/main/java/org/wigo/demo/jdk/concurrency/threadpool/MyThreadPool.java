package org.wigo.demo.jdk.concurrency.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyThreadPool {

    private int corePoolSize = 10;

    private int maxPoolSize = 20;

    private int keepAliveTime = 10;

    public MyThreadPool(int corePoolSize, int maxPoolSize, int keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    private final List<Worker> works = new ArrayList<>();

    private final BlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(100);

    private final class Worker implements Runnable {
        private Runnable task;

        private final Thread thread;

        public Worker(Runnable task) {
            this.task = task;
            this.thread = new Thread();
        }

        @Override
        public void run() {
            while (task != null) {
                // 执行任务
                task.run();
                // 获取任务
                task = getTask();
            }
        }

        private void start() {
            thread.start();
        }
    }

    private Runnable getTask() {
        Runnable task = null;
        if (works.size() < corePoolSize) {
            task = tasks.poll();
        }
        try {
            task = tasks.poll(keepAliveTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void execute(Runnable task) {
        if (works.size() < corePoolSize) {
            addWorker(task, true);
        } else if (tasks.remainingCapacity() > 0) {
            tasks.offer(task);
        } else if (works.size() < maxPoolSize) {
            addWorker(task, false);
        }

        throw new RuntimeException("not enough space");
    }

    private void addWorker(Runnable task, boolean core) {
        Worker worker = new Worker(task);
        works.add(worker);
        worker.start();
    }

}
