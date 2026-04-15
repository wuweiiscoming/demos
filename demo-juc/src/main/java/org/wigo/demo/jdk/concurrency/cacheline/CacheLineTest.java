package org.wigo.demo.jdk.concurrency.cacheline;

import org.wigo.demo.jdk.concurrency.ThreadLocalTimer;

/**
 * intel缓存行一般为64byte
 * Padding中的7个long型参数占用7*8byte=56byte
 * 加上T中的1个long型，达到64byte，迫使arr中的2个相邻的元素无法分配到同一个缓存行，反而提升了多线程写入的速度
 * 证明CPU缓存行的存在
 */
public class CacheLineTest {

    static class Padding {
        volatile long p1, p2, p3, p4, p5, p6, p7;
    }

//    static class T extends Padding {
//        volatile long t = 0L;
//    }

    static class T {
        volatile long t = 0L;
    }

    static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTimer timer = new ThreadLocalTimer();
        timer.start();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000_0000; i++) {
                arr[0].t = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000_0000; i++) {
                arr[1].t = i;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        timer.end();
    }
}
