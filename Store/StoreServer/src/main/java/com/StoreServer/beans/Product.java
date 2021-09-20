package com.StoreServer.beans;

import java.util.Objects;

public class Product {

    public Product() {
    }

    public Product(Product toBeUpdated) {
        this.id = new Integer(toBeUpdated.id);
        this.name = new String(toBeUpdated.name);
        this.quantity = new Integer(toBeUpdated.quantity);
        this.requested = new Integer(toBeUpdated.requested);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequested() {
        return requested;
    }

    public void setRequested(int requested) {
        this.requested = requested;
    }

    private int id;
    private String name;
    private int quantity;
    private int requested;
}
