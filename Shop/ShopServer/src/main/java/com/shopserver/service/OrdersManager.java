package com.shopserver.service;

import com.shopserver.beans.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class OrdersManager {

    public synchronized void manageIncompleteOrder(Order incompleteOrder) {
        incompleteOrder.getOrderedProductsIdsAndQuantity().entrySet().parallelStream().forEach(requested ->
        {
            requestedProductsQuantity.compute(requested.getKey(),
                    (k, v) -> (v == null) ? requested.getValue() : v + requested.getValue());
        });
        incompleteOrders.add(incompleteOrder);
    }

    public synchronized void updateRequestedProductsQuantity(Order completedOrder) {
        if (incompleteOrders.contains(completedOrder)) {
            completedOrder.getOrderedProductsIdsAndQuantity().entrySet().parallelStream().forEach(requested -> {
                requestedProductsQuantity.compute(requested.getKey(), (k, v) -> (v == null) ? 0 : v - requested.getValue());
            });
            incompleteOrders.remove(completedOrder);
        }
    }

    public synchronized Map<Integer, Integer> getRequestedProductsQuantity() {
        return requestedProductsQuantity;
    }

    public synchronized Set<Order> getIncompleteOrders() {
        return incompleteOrders;
    }

    private Set<Order> incompleteOrders = new LinkedHashSet<>();
    private Map<Integer, Integer> requestedProductsQuantity = new HashMap<>();
}
