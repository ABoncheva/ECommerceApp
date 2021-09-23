package com.shopserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopserver.beans.Order;
import com.shopserver.beans.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    public void processOrder(Order order) {
        Map<Integer, Integer> orderedProducts = order.getOrderedProductsIdsAndQuantity();
        Map<Integer, Integer> productsQuantityInStore = getStoreProductsQuantity();

        if (orderedProducts.entrySet().parallelStream().allMatch(productInOrder -> productInOrder.getValue() <=
                productsQuantityInStore.get(productInOrder.getKey()))) {
            finishOrder(order);
        } else {
            System.out.println("Order added to waiting list");
            ordersManager.manageIncompleteOrder(order);
        }
    }

    public void processIncompleteOrders() {
        ordersManager.getIncompleteOrders().stream().forEach(order -> processOrder(order));
    }

    public Map<Integer, Integer> getRequestedProducts() {
        return ordersManager.getRequestedProductsQuantity();
    }

    private Map<Integer, Integer> getStoreProductsQuantity() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = restTemplate.exchange(GET_PRODUCTS_QUANTITY_API, HttpMethod.GET, entity, String.class);
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

    private void finishOrder(Order order) {
        order.getOrderedProductsIdsAndQuantity().entrySet().parallelStream().forEach(x -> restTemplate.put(UPDATE_PRODUCTS_QUANTITY_API,
                new Product(x.getKey(), x.getValue() * -1)));
        ordersManager.updateRequestedProductsQuantity(order);
        System.out.println("Order finished");
    }

    private final OrdersManager ordersManager;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity entity = new HttpEntity<String>(headers);
    private static final String GET_PRODUCTS_QUANTITY_API = "http://storeserver:8080/products/quantity";
    private static final String UPDATE_PRODUCTS_QUANTITY_API = "http://storeserver:8080/products/quantity-update";
}
