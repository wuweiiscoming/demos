package org.wigo.demos.others.hotdeployment;

/**
 * 修改此类的打印信息，保存并观察打印信息变化
 */
public class TestClass {

    public TestClass() {
        System.out.println("TestClass version  5");
        System.out.println(this.getClass().getClassLoader());
    }


    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
    }
}
