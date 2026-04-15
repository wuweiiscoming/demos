package org.wigo.demo.design.pattern.structural.adapter;

/**
 * 适配器模式（Adapter Pattern）
 * 把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。
 * 实现：继承被适配的类实现目标接口
 *
 * @author wuwei31
 * @since 2021/5/16
 */
public class AdapterDemo {

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        System.out.println(adapter.output110v());
    }
}
