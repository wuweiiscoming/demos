package org.wigo.demo.design.pattern.creational.simplefactory;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class ShapeFactory {

    public static Shape getShape(String shapeType) {
        if ("circle".equals(shapeType)) {
            return new Circle();
        }
        if ("rectangle".equals(shapeType)) {
            return new Rectangle();
        }
        if ("square".equals(shapeType)) {
            return new Square();
        }
        return null;
    }

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();
    }
}
