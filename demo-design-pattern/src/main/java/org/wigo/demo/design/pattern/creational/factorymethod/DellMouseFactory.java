package org.wigo.demo.design.pattern.creational.factorymethod;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class DellMouseFactory implements MouseFactory{

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
