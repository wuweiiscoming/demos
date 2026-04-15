package org.wigo.demo.design.pattern.creational.builder;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public abstract class Builder {

    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    public Product getResult(){
        return product;
    }
}
