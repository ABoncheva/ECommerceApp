package com.shopserver.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {

    public Order(int id, Map<Integer, Integer> orderedProducts) {
        this.id = id;
        this.orderedProductsIdsAndQuanttity = orderedProducts;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getOrderedProductsIdsAndQuanttity() {
        return orderedProductsIdsAndQuanttity;
    }

    public void setOrderedProductsIdsAndQuanttity(Map<Integer, Integer> orderedProductsIdsAndQuanttity) {
        this.orderedProductsIdsAndQuanttity = orderedProductsIdsAndQuanttity;
    }

    private int id;
    private Map<Integer, Integer> orderedProductsIdsAndQuanttity = new HashMap<>();
}
