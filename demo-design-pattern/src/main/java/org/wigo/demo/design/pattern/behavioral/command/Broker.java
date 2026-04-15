package org.wigo.demo.design.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuwei31
 * @since 2021/5/24
 */
public class Broker {

    private List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    public void placeOrders() {
        for (Order order : orders) {
            order.execute();
        }
        orders.clear();
    }
}
