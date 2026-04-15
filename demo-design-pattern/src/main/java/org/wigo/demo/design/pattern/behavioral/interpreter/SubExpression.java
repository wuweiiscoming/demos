package org.wigo.demo.design.pattern.behavioral.interpreter;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class SubExpression extends AbstractNonTerminalExpression {

    public SubExpression(IExpression right, IExpression left) {
        super(right, left);
    }

    @Override
    public int interpret() {
        return right.interpret() - left.interpret();
    }
}
