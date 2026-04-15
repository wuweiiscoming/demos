package org.wigo.demo.design.pattern.creational.factorymethod;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class DellMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("click with dell mouse");
    }
}
