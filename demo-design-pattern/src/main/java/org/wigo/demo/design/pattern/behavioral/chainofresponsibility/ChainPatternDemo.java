package org.wigo.demo.design.pattern.behavioral.chainofresponsibility;

/**
 * @program: design-patterns
 * @description: 责任链模式
 * @author: wuwei31
 * @since 2021/5/11
 */
public class ChainPatternDemo {

    public static void main(String[] args) {
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.DEBUG);
        errorLogger.setNextLogger(consoleLogger);
        FileLogger fileLogger = new FileLogger(AbstractLogger.ERROR);
        fileLogger.setNextLogger(errorLogger);
        fileLogger.logMessage(3,"this is a log message");
    }
}
