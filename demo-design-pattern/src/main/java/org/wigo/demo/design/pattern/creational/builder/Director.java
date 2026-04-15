package org.wigo.demo.design.pattern.creational.builder;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct(){
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
