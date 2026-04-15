package org.wigo.demo.design.pattern.behavioral.interpreter;

/**
 * 解释器模式
 * 是指给定一门语言，定义它的文法的一种表示(如：加减乘除表达式和正则表达式等)，然后再定义一个解释器，该解释器用来解释我们的文法表示(表达式)
 * <p>
 * 抽象表达式(Expression)：一般会定义一个解释方法，具体如何解析会交由子类进行实现(如示例中的IExpression)。
 * 终结符表达式(Terminal Expression)：实现文法中与终结符有关的解释操作(如示例中的AddExpression,SubExpression)。
 * 非终符结表达式(Nonterminal Expression)：实现文法中与非终结符有关的解释操作(如示例中的NumberExpression)。
 * 上下文环境(Context)：包含解释器之外的全局信息。一般用来ExpressionParseExpressionParse存放文法中各个终结符所对应的具体值。
 * <p>
 * 应用
 * JDK中的正则表达式
 * Spring中的ExpressionParser
 *
 * @author wuwei31
 * @since 2021/5/24
 */
public class InterpreterPatternDemo {

    public static void main(String[] args) {
        ExpressionContext context = new ExpressionContext("1 + 2 - 3 + 4 - 5 + 6");
        System.out.println(context.calculate());
    }
}
