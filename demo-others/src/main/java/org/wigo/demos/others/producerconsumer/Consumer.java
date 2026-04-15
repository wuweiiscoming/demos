package org.wigo.demos.others.producerconsumer;

import java.util.List;

/**
 * 消费者
 *
 * @author Wu Wei
 * @since 2019-04-17 13:53
 */
public class Consumer implements Runnable{

    private final Object object;

    private List<String> list;

    public Consumer(Object object, List<String> list) {
        this.object = object;
        this.list = list;
    }

    public void consume(){
        synchronized (object){
            while(list.isEmpty()){
                try {
                    System.out.println("队列已空，消费等待...");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove(0);
            System.out.println("队列已空，通知生产...");
            object.notifyAll();
        }
    }

    @Override
    public void run() {
        consume();
    }
}
