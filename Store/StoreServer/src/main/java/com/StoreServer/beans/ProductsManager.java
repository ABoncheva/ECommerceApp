package com.StoreServer.beans;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProductsManager {

    public static ProductsManager getInstance() {
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

    public synchronized Map<Integer, Integer> getInsufficientProducts() {

        Map<Integer, Integer> productsAndHowManyNeedToBeAdded = new HashMap<>();
        products.stream().filter(product -> product.getRequested() > product.getQuantity()).forEach(product -> {
            productsAndHowManyNeedToBeAdded.put(product.getId(), product.getRequested() - product.getQuantity());
        });

        return productsAndHowManyNeedToBeAdded;
    }


    public Collection<Product> getProducts() {
        return products;
    }

    private ProductsManager() {
        products = ConcurrentHashMap.newKeySet();
        Product test = new Product();
        test.setId(1);
        test.setName("Aleksandrina");
        test.setQuantity(1);
        test.setRequested(10);
        products.add(test);
    }

    private Set<Product> products;
    private static ProductsManager productsManager = null;
}
