package org.wigo.demo.design.pattern.behavioral.interpreter;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class NumberExpression implements IExpression {

    private int value;

    public NumberExpression(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public int interpret() {
        return value;
    }
}
