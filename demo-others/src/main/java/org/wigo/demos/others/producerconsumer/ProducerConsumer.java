package org.wigo.demos.others.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者模式
 *
 * @author Wu Wei
 * @since 2019-04-16 11:50
 */
public class ProducerConsumer {


    public static void main(String[] args) {
        Object object = new Object();
        List<String> list = new ArrayList<>();
        Consumer consumer = new Consumer(object, list);
        Producer producer = new Producer(object, list);
        for (int i = 0; i < 5; i++) {
            new Thread(consumer).start();
            new Thread(producer).start();
        }
    }
}
