package org.wigo.demo.design.pattern.behavioral.observer;

/**
 * @author wuwei31
 * @since 2021/5/11
 */
public abstract class Observer {

    Observer(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }

    protected Subject subject;

    abstract void update();
}
