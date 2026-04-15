package org.wigo.demo.jdk.concurrency;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wuwei
 * @since 2021/3/2 上午8:44
 */
public class ParkTest {

    /**
     * LockSupport.park();
     * 可以被中断
     */
    @Test
    public void case1() throws InterruptedException {

        Thread thread = new Thread(()->{

            System.out.println("park");

            LockSupport.park();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("再次park");

            LockSupport.park();

            System.out.println("被中断");

        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("unpark");

        LockSupport.unpark(thread);

        Thread.sleep(2000);

        System.out.println("interrupt");

        thread.interrupt();

        Thread.sleep(Integer.MAX_VALUE);
    }


    /**
     * 定时park
     */
    @Test
    public void case2() {
        System.out.println("park1秒");

        LockSupport.parkNanos(1_000_000_000L);

        System.out.println("结束");
    }


}
