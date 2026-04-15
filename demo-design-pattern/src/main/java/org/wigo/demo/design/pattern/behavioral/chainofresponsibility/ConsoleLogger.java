package org.wigo.demo.design.pattern.behavioral.chainofresponsibility;

/**
 * @author wuwei31
 * @since 2021/5/11
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        super(level);
    }

    @Override
    protected void write(String message) {
        System.out.println("ConsoleLogger:" + message);
    }
}
