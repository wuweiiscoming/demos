package org.wigo.demo.design.pattern.behavioral.observer;

/**
 * @author wuwei31
 * @since 2021/5/11
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    void update() {
        System.out.println("Octal String:"+Integer.toOctalString(subject.getState()));
    }
}
