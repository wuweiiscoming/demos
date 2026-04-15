package org.wigo.demo.design.pattern.creational.builder;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class ConcreteBuilder extends Builder{

    @Override
    public void buildPartA() {
        product.setPartA("建造 partA");
    }

    @Override
    public void buildPartB() {
        product.setPartB("建造 partB");
    }

    @Override
    public void buildPartC() {
        product.setPartC("建造 partC");
    }
}
