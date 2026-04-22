package org.wigo.demo.jdk.jdk17.sealed;

/**
 * JDK 17 密封类（Sealed Classes）示例
 *
 * 密封类限制哪些类可以继承或实现它。
 * 使用 sealed 关键字声明，permits 指定允许的子类。
 * 子类必须是 final、sealed 或 non-sealed。
 *
 * 面试要点：
 * 1. sealed - 密封类，必须指定允许的子类
 * 2. permits - 指定允许继承的子类
 * 3. 子类必须使用 final/sealed/non-sealed 修饰
 */
public abstract sealed class Shape permits Circle, Rectangle, Triangle {

    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double area();
}

final class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }
}

final class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

// non-sealed 表示开放继承，不受密封限制
non-sealed class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

// 演示类
class SealedClassDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0);

        System.out.println(circle.getName() + " area: " + circle.area());
        System.out.println(rectangle.getName() + " area: " + rectangle.area());
        System.out.println(triangle.getName() + " area: " + triangle.area());

        // 使用 instanceof 模式匹配
        if (circle instanceof Circle c) {
            System.out.println("Circle radius: " + c.getRadius());
        }
    }
}