package com.StoreClient.beans;

import java.util.Objects;

public class Product {

    public Product(int id, int quantityTobBeAdded) {
        this.id = id;
        this.quantityTobBeAdded = quantityTobBeAdded;
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

    private int id;
    private int quantityTobBeAdded;
}
