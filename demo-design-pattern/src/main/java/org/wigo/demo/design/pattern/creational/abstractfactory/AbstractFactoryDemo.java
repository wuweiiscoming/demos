package org.wigo.demo.design.pattern.creational.abstractfactory;

/**
 * 提供一个创建一系列相关或者相互依赖对象的接口，而无需指定他们具体的类
 *
 * @author wuwei31
 * @since 2021/5/14
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {
        PcFactory pcFactory = new DellFactory();
        Mouse mouse = pcFactory.createMouse();
        Keyboard keyboard = pcFactory.createKeyboard();
        mouse.click();
        keyboard.hit();
    }
}
