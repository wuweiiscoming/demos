package org.wigo.demos.others.zookeeper.lock;

public class Stock {

    private static Integer COUNT = 1;

    public boolean reduceStock() {
        if (COUNT > 0) {
            COUNT--;
            System.out.println(Thread.currentThread().getName() + "减库存成功");
            return true;
        }
        System.out.println(Thread.currentThread().getName() + "减库存失败");
        return false;
    }
}
