package org.wigo.demo.jdk.concurrency.threadpool;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wuwei31
 * @since 2021/5/5
 */
public class ScheduledThreadPoolExecutorTest {


    @Test
    public void scheduleAtFixedRate() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("执行任务");
        },1,1, TimeUnit.SECONDS);

        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 等待任务执行结束再计算执行周期
     * @throws InterruptedException
     */
    @Test
    public void scheduleWithFixedDelay() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleWithFixedDelay(()->{
            System.out.println("执行任务"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行结束"+System.currentTimeMillis());

        },1,1, TimeUnit.SECONDS);


        Thread.sleep(Integer.MAX_VALUE);
    }


}
