package org.wigo.demo.jdk.jdk21.recordpattern;

/**
 * JDK 21 Record Pattern（记录模式）示例
 *
 * Record Pattern 允许直接在模式匹配中解构 Record。
 *
 * 面试要点：
 * 1. Record Pattern 可以解构 Record 的组件
 * 2. 支持嵌套解构
 * 3. 配合 instanceof 和 switch 使用
 */
public class RecordPatternDemo {

    record Point(int x, int y) {}

    record Rectangle(Point upperLeft, Point lowerRight) {}

    record Circle(Point center, int radius) {}

    public static void main(String[] args) {
        // instanceof 记录模式
        Object obj = new Point(10, 20);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("Point: x=" + x + ", y=" + y);
        }

        // 嵌套解构
        Object shape = new Rectangle(new Point(0, 10), new Point(20, 0));
        if (shape instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("Rectangle: (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");
        }

        // switch 中的记录模式
        System.out.println("\n=== switch 记录模式 ===");
        Object[] shapes = {
            new Point(5, 10),
            new Rectangle(new Point(0, 5), new Point(10, 0)),
            new Circle(new Point(5, 5), 3)
        };

        for (Object s : shapes) {
            String desc = describeShape(s);
            System.out.println(desc);
        }
    }

    static String describeShape(Object shape) {
        return switch (shape) {
            case Point(int x, int y) -> "Point at (" + x + ", " + y + ")";
            case Rectangle(Point ul, Point lr) ->
                "Rectangle from (" + ul.x() + "," + ul.y() + ") to (" + lr.x() + "," + lr.y() + ")";
            case Circle(Point c, int r) ->
                "Circle at (" + c.x() + "," + c.y() + ") with radius " + r;
            case null -> "null shape";
            default -> "Unknown shape";
        };
    }
}