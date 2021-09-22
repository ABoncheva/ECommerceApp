package com.storeserver.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
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
            if (product.getQuantity() > 0) { // if quantity < 0 , that means that somebody ordered this product
                asyncMsgSender.convertAndSend("product-quantity-increased");
            }
        }
    }


    public Map<Integer, Integer> getInsufficientProducts() throws JsonProcessingException {
        Map<Integer, Integer> requestedProducts = getRequestedProducts();
        Map<Integer, Integer> result = new HashMap<>();
        products.stream().forEach(product -> {
            int requestedAndInStockDelta = requestedProducts.get(product.getId()) - product.getQuantity();
            if (requestedProducts.keySet().contains(product) &&
                    requestedAndInStockDelta > 0) {
                result.put(product.getId(), requestedAndInStockDelta);
            } else {
                result.put(product.getId(), 0);
            }
        });
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

    private Map<Integer, Integer> getRequestedProducts() throws JsonProcessingException {
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

        return mapper.readValue(jsonInput, typeRef);
    }

    private ProductsManager() {
        products = ConcurrentHashMap.newKeySet();
        Product test = new Product();
        Product test2 = new Product();
        test.setId(1);
        test.setName("Aleksandrina2");
        test.setQuantity(5);
        products.add(test2);
        products.add(test);
    }

    private Set<Product> products;
    private static ProductsManager productsManager = null;
    private static final String GET_REQUESTED_PRODUCTS_QUANTITY_API = "http://localhost:8080/products/requested";
    private AmqpTemplate asyncMsgSender;
}
