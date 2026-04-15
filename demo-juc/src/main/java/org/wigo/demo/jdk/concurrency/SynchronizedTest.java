package org.wigo.demo.jdk.concurrency;

/**
 * @author wuwei
 * @since 2021/2/28 下午8:33
 */
public class SynchronizedTest {

    /**
     * 静态方法锁的是SynchronizedTest.class对象
     */
    private synchronized static void test1() {
        System.out.println("test1");
    }

    private void test2() {
        synchronized (SynchronizedTest.class) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2");
        }
    }


    public static void main(String[] args) {
        new Thread(() -> {
            SynchronizedTest test = new SynchronizedTest();
            test.test2();
        }).start();
        test1();
    }
}
