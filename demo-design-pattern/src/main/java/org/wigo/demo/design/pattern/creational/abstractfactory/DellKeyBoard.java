package org.wigo.demo.design.pattern.creational.abstractfactory;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class DellKeyBoard implements Keyboard{

    @Override
    public void hit() {
        System.out.println("hit dell keyboard");
    }
}
