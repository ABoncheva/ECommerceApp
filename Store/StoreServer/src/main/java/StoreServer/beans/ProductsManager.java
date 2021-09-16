package StoreServer.beans;

import java.util.*;

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

    public void updateProductQuantity(Product product) {
        for (Product toBeUpdated : products) {
            if (toBeUpdated.equals(product)) {
                Product toBeAdded = new Product(toBeUpdated);
                toBeAdded.setQuantity(product.getQuantity());
                products.remove(toBeUpdated);
                products.add(toBeAdded);
                break;
            }
        }
    }

    public void updateProductRequested(Product product) {
//        for (Product toBeUpdated : products) {
//            if (toBeUpdated.equals(product)) {
//                toBeUpdated.setRequested(product.getRequested());
//            }
//        }
    }

    public Map<String, Integer> getAllInsufficientProducts() {

        Map<String, Integer> productsAndHowManyNeedToBeAdded = new HashMap<>();
        products.stream().filter(product -> product.getRequested() > product.getQuantity()).forEach(product -> {
            productsAndHowManyNeedToBeAdded.put(product.getName(), product.getRequested() - product.getQuantity());
        });

        return productsAndHowManyNeedToBeAdded;
    }


    public Collection<Product> getAllProducts() {
        return products;
    }

    private ProductsManager() {
        products = new HashSet<>();
    }

    private Set<Product> products;
    private static ProductsManager productsManager = null;
}
