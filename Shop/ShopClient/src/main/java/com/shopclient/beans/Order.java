package com.shopclient.beans;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public Order() {}

    public Order(int id, Map<Integer, Integer> orderedProducts) {
        this.id = id;
        this.orderedProductsIdsAndQuantity = orderedProducts;
    }

    private int id;
    private Map<Integer, Integer> orderedProductsIdsAndQuantity = new HashMap<>();
}
