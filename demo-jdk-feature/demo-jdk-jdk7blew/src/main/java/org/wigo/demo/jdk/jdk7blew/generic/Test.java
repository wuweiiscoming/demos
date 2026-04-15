package org.wigo.demo.jdk.jdk7blew.generic;

/**
 * @author wuwei31
 * @since 2021/5/25
 */
public class Test {


    public <T> Pair<T> cast(Pair<T> pair){
        return new Pair<T>(pair.t);
    }

    public static void main(String[] args) {
        Pair<String> pair = new Pair<>("s");
        Test test = new Test();
        Pair<String> cast = test.cast(pair);
    }
}
