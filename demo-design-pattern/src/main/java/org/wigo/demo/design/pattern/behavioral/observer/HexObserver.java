package org.wigo.demo.design.pattern.behavioral.observer;

/**
 * @author wuwei31
 * @since 2021/5/11
 */
public class HexObserver extends Observer{

    public HexObserver(Subject subject) {
        super(subject);
    }

    @Override
    void update() {
        System.out.println("Hex String:"+Integer.toHexString(subject.getState()));
    }


}
