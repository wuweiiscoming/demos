package org.wigo.demo.jdk.jdk8.interfacedefaultmethod;

/**
 * @author wuwei
 * @since 2020/12/26 1:55 下午
 */
public interface InterfaceWithDefaultMethod {

    default void method(){
        System.out.println("default method from interface");
    }

    // 无法覆盖Object类的方法
//    default String toString(){
//    }

    // 允许提供静态方法
    static void staticMethod(){
        System.out.println("static method from interface");
    }

    // 意味着可以执行main方法
     static void main(String[] args) {
        staticMethod();
    }
}
