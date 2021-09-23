package com.storeserver.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storeserver.beans.Product;
import com.storeserver.producers.ProductsQuantityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductsManager {

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
        if (product.getQuantity() > 0) { // if quantity < 0 , that means that somebody ordered this product
            productsQuantityProducer.publish(productsQuantityProducer.PRODUCT_QUANTITY_INCREASED_NOTIFICATION_MSG);
            // left here for the demo
            System.out.println("Product quantity increased");
        }
    }


    public Map<Integer, Integer> getInsufficientProducts() {
        Map<Integer, Integer> requestedProducts = getRequestedProducts();
        Map<Integer, Integer> result = new HashMap<>();
        synchronized (this) {
            products.parallelStream().forEach(product -> {
                int requestedAndInStockDelta = requestedProducts.get(product.getId()) - product.getQuantity();
                if (requestedProducts.keySet().contains(product) &&
                        requestedAndInStockDelta > 0) {
                    result.put(product.getId(), requestedAndInStockDelta);
                } else {
                    result.put(product.getId(), 0);
                }
            });
        }
        return result;
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

    private Map<Integer, Integer> getRequestedProducts() {
        RestTemplate restClient = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<String>(headers);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = restClient.exchange(GET_REQUESTED_PRODUCTS_QUANTITY_API,
                HttpMethod.GET, entity, String.class);
        String jsonInput = response.getBody();
        TypeReference<HashMap<Integer, Integer>> typeRef
                = new TypeReference<>() {
        };
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonInput, typeRef);
        } catch (JsonProcessingException exception) {
            System.err.println("A problem occurred with parsing ShopServer response");
        }
        return null;
    }

    private ProductsManager(@Autowired ProductsQuantityProducer productsQuantityProducer) {
        this.productsQuantityProducer = productsQuantityProducer;
        products = ConcurrentHashMap.newKeySet();

        // hardcoded for the demo
        Product test = new Product();
        test.setId(2);
        test.setName("testProduct");
        test.setQuantity(2);
        products.add(test);
    }

    private final ProductsQuantityProducer productsQuantityProducer;
    private Set<Product> products;
    private static final String GET_REQUESTED_PRODUCTS_QUANTITY_API = "http://shopserver:8081/products/requested";
}
