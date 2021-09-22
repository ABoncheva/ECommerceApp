package com.shopserver.beans;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class OrdersManager {

    public static synchronized OrdersManager getInstance() {
        if (ordersManager == null) {
            ordersManager = new OrdersManager();
        }

        return ordersManager;
    }

    public void manageIncompleteOrder(Order incompleteOrder) {
        incompleteOrder.getOrderedProductsIdsAndQuantity().entrySet().stream().forEach(requested ->
        {
            requestedProductsQuantity.compute(requested.getKey(),
                    (k, v) -> (v == null) ? requested.getValue() : v + requested.getValue());
        });
        incompleteOrders.add(incompleteOrder);
    }

    public void updateRequestedProductsQuantity(Order completedOrder) {
        completedOrder.getOrderedProductsIdsAndQuantity().entrySet().stream().forEach(requested -> {
            requestedProductsQuantity.compute(requested.getKey(), (k, v) -> (v == null) ? 0 : v - requested.getValue());
        });
    }

    public Map<Integer, Integer> getRequestedProductsQuantity() {
        return requestedProductsQuantity;
    }


    private OrdersManager() {
    }

    private Set<Order> incompleteOrders = new LinkedHashSet<>();
    private Map<Integer, Integer> requestedProductsQuantity = new HashMap<>();
    private static OrdersManager ordersManager = null;
}
