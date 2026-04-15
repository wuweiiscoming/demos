package org.wigo.demo.design.pattern.behavioral.observer;

/**
 * @author wuwei31
 * @since 2021/5/11
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    void update() {
        System.out.println("Binary String:"+Integer.toBinaryString(subject.getState()));
    }
}
