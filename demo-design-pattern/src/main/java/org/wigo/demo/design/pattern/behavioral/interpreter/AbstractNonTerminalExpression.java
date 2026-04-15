package org.wigo.demo.design.pattern.behavioral.interpreter;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public abstract class AbstractNonTerminalExpression implements IExpression{

    protected IExpression right;
    protected IExpression left;

    public AbstractNonTerminalExpression(IExpression right, IExpression left) {
        this.right = right;
        this.left = left;
    }
}
