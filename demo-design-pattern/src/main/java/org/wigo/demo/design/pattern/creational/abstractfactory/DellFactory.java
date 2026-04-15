package org.wigo.demo.design.pattern.creational.abstractfactory;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class DellFactory implements PcFactory{

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyBoard();
    }
}
