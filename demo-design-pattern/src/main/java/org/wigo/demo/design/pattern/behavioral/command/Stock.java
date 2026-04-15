package org.wigo.demo.design.pattern.behavioral.command;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class Stock {

    private String name = "ABC";

    private int quantity = 10;

    public void buy() {
        System.out.println("buy stock");
    }

    public void sell() {
        System.out.println("sell stock");
    }

}
