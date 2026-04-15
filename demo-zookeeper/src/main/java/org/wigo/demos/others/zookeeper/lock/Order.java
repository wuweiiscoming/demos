package org.wigo.demos.others.zookeeper.lock;

public class Order {

    public void createOrder(){
        System.out.println(Thread.currentThread().getName()+"创建order");
    }
}
