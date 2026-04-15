package org.wigo.demo.design.pattern.creational.factorymethod;

/**
 * 定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使创建过程延迟到了子类进行
 *
 * @author wuwei31
 * @since 2021/5/14
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        MouseFactory mouseFactory = new DellMouseFactory();
        Mouse mouse = mouseFactory.createMouse();
        mouse.click();
    }
}
