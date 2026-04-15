package org.wigo.demo.design.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知依赖它的对象。
 *
 * @author wuwei31
 * @since 2021/5/11
 */
public class Subject {

    private int state;

    List<Observer> observers = new ArrayList<>();

    void attach(Observer observer){
        observers.add(observer);
    }

    void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        HexObserver hexObserver = new HexObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);
        subject.setState(11);
    }
}
