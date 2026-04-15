package org.wigo.demo.dubbo.spi.javaspi;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
public class Dog implements Animal{

    @Override
    public void bark() {
        System.out.println("woof woof");
    }
}
