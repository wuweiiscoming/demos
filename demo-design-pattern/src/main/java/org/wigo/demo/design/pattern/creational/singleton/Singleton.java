package org.wigo.demo.design.pattern.creational.singleton;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton(){
    }

    public static Singleton get(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
