package org.wigo.demo.design.pattern.creational.simplefactory;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("draw a rectangle");
    }
}
