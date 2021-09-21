package com.storeserver.beans;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProductsManager {

    public static synchronized ProductsManager getInstance() {
        if (productsManager == null) {
            productsManager = new ProductsManager();
        }
        return productsManager;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public synchronized void updateProductQuantity(Product product) {
        for (Product toBeUpdated : products) {
            if (toBeUpdated.equals(product)) {
                Product toBeAdded = new Product(toBeUpdated);
                toBeAdded.setQuantity(toBeAdded.getQuantity() + product.getQuantity());
                products.remove(toBeUpdated);
                products.add(toBeAdded);
                break;
            }
        }
    }

    public synchronized void updateProductRequested(Product product) {
        for (Product toBeUpdated : products) {
            if (toBeUpdated.equals(product)) {
                Product toBeAdded = new Product(toBeUpdated);
                toBeAdded.setRequested(product.getRequested());
                products.remove(toBeUpdated);
                products.add(toBeAdded);
                break;
            }
        }
    }

    // to be fixed
    public Map<Integer, Integer> getInsufficientProducts() {

        Map<Integer, Integer> productsAndHowManyNeedToBeAdded = new HashMap<>();
        synchronized (this) {
            products.stream().filter(product -> product.getRequested() > product.getQuantity()).forEach(product -> {
                productsAndHowManyNeedToBeAdded.put(product.getId(), product.getRequested() - product.getQuantity());
            });
        }

        return productsAndHowManyNeedToBeAdded;
    }

    public Map<Integer, Integer> getProductsQuantity() {
        Map<Integer, Integer> productsIdAndQuantity = new HashMap<>();

        synchronized (this) {
            products.parallelStream().forEach(product -> {
                productsIdAndQuantity.put(product.getId(), product.getQuantity());
            });
        }

        return productsIdAndQuantity;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    private ProductsManager() {
        products = ConcurrentHashMap.newKeySet();
        Product test = new Product();
        Product test2 = new Product();
        test.setId(2);
        test.setName("Aleksandrina2");
        test.setQuantity(2);
        products.add(test2);
        products.add(test);
    }

    private Set<Product> products;
    private static ProductsManager productsManager = null;
}
