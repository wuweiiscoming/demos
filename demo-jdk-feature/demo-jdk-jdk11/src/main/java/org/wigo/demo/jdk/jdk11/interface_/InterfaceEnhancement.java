package org.wigo.demo.jdk.jdk11.interface_;

/**
 * JDK 9~11 接口增强示例
 *
 * JDK 8: 接口可以有 default 方法
 * JDK 9: 接口可以有 private 方法
 *
 * 面试要点：
 * 1. private 方法用于复用 default 方法的公共逻辑
 * 2. private 方法不能被实现类访问
 * 3. private 方法可以是 static 或 non-static
 * 4. 解决了 default 方法代码重复问题
 */
public interface InterfaceEnhancement {

    // JDK 8: default 方法
    default void defaultMethod1() {
        commonLogic();  // 调用 private 方法复用逻辑
        System.out.println("Default method 1");
    }

    default void defaultMethod2() {
        commonLogic();  // 复用 private 方法
        System.out.println("Default method 2");
    }

    // JDK 9: private 方法，用于 default 方法之间复用代码
    private void commonLogic() {
        System.out.println("Common private logic");
    }

    // JDK 9: private static 方法
    private static void staticPrivateMethod() {
        System.out.println("Private static method");
    }

    // static 方法可以调用 private static 方法
    static void staticMethod() {
        staticPrivateMethod();
        System.out.println("Static method");
    }
}

class InterfaceDemo implements InterfaceEnhancement {
    public static void main(String[] args) {
        InterfaceDemo demo = new InterfaceDemo();
        demo.defaultMethod1();
        demo.defaultMethod2();

        // static 方法调用
        InterfaceEnhancement.staticMethod();

        // 不能调用 private 方法
        // demo.commonLogic();  // 编译错误
    }
}