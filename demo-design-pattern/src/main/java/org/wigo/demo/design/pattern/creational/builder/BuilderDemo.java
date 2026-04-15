package org.wigo.demo.design.pattern.creational.builder;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class BuilderDemo {

    public static void main(String[] args) {

        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        System.out.println(product.show());
    }
}
