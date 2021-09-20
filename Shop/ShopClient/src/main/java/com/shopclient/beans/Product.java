package com.shopclient.beans;

import java.util.Objects;

public class Product {

    public Product(int id, int quantityToBeOrdered) {
        this.id = id;
        this.quantityTobBeOrdered = quantityToBeOrdered;
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

    private final int id;
    private int quantityTobBeOrdered;
}
