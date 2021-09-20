package com.shopserver.beans;

import java.util.HashMap;
import java.util.Map;

public class OrdersManager {

    // maybe synchronized
    public static OrdersManager getInstance() {
        if (ordersManager == null) {
            ordersManager = new OrdersManager();
        }

        return ordersManager;
    }

    public void addOrder(Order newOrder) {
        ordersAndStatuses.put(newOrder, INCOMPLETED_ORDER_STATUS_CODE);
    }

    private OrdersManager() {
    }

    private Map<Order, Integer> ordersAndStatuses = new HashMap<>();
    private static OrdersManager ordersManager = null;
    private static final int INCOMPLETED_ORDER_STATUS_CODE = 0;
    private static final int COMPLETED_ORDER_STATUS_CODE = 1;
}
