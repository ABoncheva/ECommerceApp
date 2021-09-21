package com.shopserver.beans;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class OrdersManager {

    // maybe synchronized
    public static OrdersManager getInstance() {
        if (ordersManager == null) {
            ordersManager = new OrdersManager();
        }

        return ordersManager;
    }

    public void addOrder(Order newOrder) {
        incompletedOrders.add(newOrder);
    }

    private OrdersManager() {
    }

    private BlockingDeque<Order> incompletedOrders = new LinkedBlockingDeque<>();
    private static OrdersManager ordersManager = null;
}
