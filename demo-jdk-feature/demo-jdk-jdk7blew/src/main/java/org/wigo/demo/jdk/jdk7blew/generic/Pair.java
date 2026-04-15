package org.wigo.demo.jdk.jdk7blew.generic;

/**
 * @author wuwei31
 * @since 2021/4/26
 */
public class Pair<T> {

    public Pair(T t) {
        this.t = t;
    }

    T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

}
