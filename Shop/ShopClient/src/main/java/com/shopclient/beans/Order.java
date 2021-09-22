package com.shopclient.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getOrderedProductsIdsAndQuantity() {
        return orderedProductsIdsAndQuantity;
    }

    public void setOrderedProductsIdsAndQuantity(Map<Integer, Integer> orderedProductsIdsAndQuantity) {
        this.orderedProductsIdsAndQuantity = orderedProductsIdsAndQuantity;
    }

    public Order(int id, Map<Integer, Integer> orderedProducts) {
        this.id = id;
        this.orderedProductsIdsAndQuantity = orderedProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private int id;
    private Map<Integer, Integer> orderedProductsIdsAndQuantity = new HashMap<>();
}
