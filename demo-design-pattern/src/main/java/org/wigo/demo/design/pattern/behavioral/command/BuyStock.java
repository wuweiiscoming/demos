package org.wigo.demo.design.pattern.behavioral.command;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class BuyStock implements Order{

    private Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}
