package com.shopserver.beans;

import java.util.Objects;

public class Product {

    public Product() {
    }

    ;

    public Product(int id, int quantityToBeOrdered) {
        this.id = id;
        this.quantity = quantityToBeOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
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

    public int getQuantityTobBeOrdered() {
        return quantity;
    }

    public void setQuantityTobBeOrdered(int quantityTobBeOrdered) {
        this.quantity = quantityTobBeOrdered;
    }

    private int id;
    private int quantity;
}
