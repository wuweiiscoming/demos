package org.wigo.demo.design.pattern.behavioral.interpreter;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class AddExpression extends AbstractNonTerminalExpression {

    public AddExpression(IExpression right, IExpression left) {
        super(right, left);
    }

    @Override
    public int interpret() {
        return right.interpret() + left.interpret();
    }
}
