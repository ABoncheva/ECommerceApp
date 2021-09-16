package beans;

import java.util.HashSet;
import java.util.Set;

public class ProductsManager {

    public static ProductsManager getInstance() {
        if (productsManager == null) {
            productsManager = new ProductsManager();
            return productsManager;
        } else {
            return productsManager;
        }
    }

    public void add(Product product) {
        products.add(product);
    }

    public void updateProduct(Product product) {
       products.stream().forEach(productInStore -> {
           if (product.equals(productInStore)) {
               productInStore.setQuantity(product.getQuantity());
           }
       });
    }

    private ProductsManager() {
        products = new HashSet<>();
    }

    private Set<Product> products;
    private static ProductsManager productsManager = null;
}
