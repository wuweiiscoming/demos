package org.wigo.demo.jdk.jdk8.lambda;

/**
 * @author wuwei
 * @since 2020/12/26 2:11 下午
 */
@FunctionalInterface
public interface LambdaInterface {

    void doSomething();

    static void main(String[] args) {
        LambdaInterface lambda = () -> System.out.println("hello");
        lambda.doSomething();
    }

}
