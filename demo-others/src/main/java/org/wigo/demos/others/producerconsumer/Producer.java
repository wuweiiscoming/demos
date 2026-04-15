package org.wigo.demos.others.producerconsumer;

import java.util.List;

/**
 * 生产者
 *
 * @author Wu Wei
 * @since 2019-04-17 11:52
 */
public class Producer implements Runnable{

    private final Object object;

    private List<String> list;

    public Producer(Object object, List<String> list) {
        this.object = object;
        this.list = list;
    }

    public void produce(){
        synchronized (object){
            while(!list.isEmpty()){
                try {
                    System.out.println("队列已满，生产等待...");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add("xxx");
            System.out.println("队列已满，通知消费...");
            object.notifyAll();
        }
    }

    @Override
    public void run() {
        produce();
    }
}
