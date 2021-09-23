package com.storeserver.beans;

import java.util.Objects;

public class Product {

    public Product() {
    }

    public Product(Product toBeUpdated) {
        this.id = Integer.valueOf(toBeUpdated.id);
        this.quantity = Integer.valueOf(toBeUpdated.quantity);
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private int id;
    private int quantity;
}
