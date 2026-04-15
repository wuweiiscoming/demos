package org.wigo.demo.jdk.jdk9;

/**
 * 允许接口实现private方法
 *
 * @author wuwei
 * @since 2020/12/27 上午11:35
 */
public interface InterfaceWithPrivateMethod {

    private void privateMethod(){
        System.out.println("a private method");
    }

}
