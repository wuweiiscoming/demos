package org.wigo.demo.design.pattern.structural.adapter;

/**
 * @author wuwei31
 * @since 2021/5/16
 */
public class Adapter implements Target {

    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public int output110v() {
        int v220 = adaptee.output220v();
        // 将220v转换为110v
        return v220 / 2;
    }
}
