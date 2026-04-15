package org.wigo.demo.design.pattern.behavioral.interpreter;

import java.util.Stack;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class ExpressionContext {

    private IExpression expression;

    public ExpressionContext(String str) {
        String[] arr = str.split(" ");
        Stack<IExpression> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            String element = arr[i];
            if (element.equals("+")) {
                IExpression left = stack.pop();
                IExpression right = new NumberExpression(arr[++i]);
                stack.push(new AddExpression(left, right));
            } else if (element.equals("-")) {
                IExpression left = stack.pop();
                IExpression right = new NumberExpression(arr[++i]);
                stack.push(new SubExpression(left, right));
            } else {
                stack.push(new NumberExpression(element));
            }
        }
        expression = stack.pop();
    }

    public int calculate() {
        return expression.interpret();
    }
}
