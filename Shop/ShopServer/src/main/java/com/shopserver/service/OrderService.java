package com.shopserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopserver.beans.Order;
import com.shopserver.beans.OrdersManager;
import com.shopserver.beans.Product;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

//    public static void main(String[] args) throws JsonProcessingException {
//        Map<Integer, Integer> products = new HashMap<>();
//        products.put(2, 3);
//        Order order = new Order(1, products);
//        processOrder(order);
//    }

    public static void processOrder(Order order) throws JsonProcessingException {
        Map<Integer, Integer> orderedProducts = order.getOrderedProductsIdsAndQuantity();
        Map<Integer, Integer> productsQuantityInStore = getStoreProductsQuantity();
        if (orderedProducts.entrySet().parallelStream().allMatch(productInOrder -> productInOrder.getValue() <=
                productsQuantityInStore.get(productInOrder.getKey()) == true)) {
            finishOrder(orderedProducts);
            OrdersManager.getInstance().updateRequestedProductsQuantity(order);
        } else {
            System.out.println("Order inserted in waiting");
            OrdersManager.getInstance().manageIncompleteOrder(order);
        }
    }

    public static Map<Integer, Integer> getRequestedProducts() {
        return OrdersManager.getInstance().getRequestedProductsQuantity();
    }

    private static Map<Integer, Integer> getStoreProductsQuantity() throws JsonProcessingException {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = restTemplate.exchange(GET_PRODUCTS_QUANTITY_API, HttpMethod.GET, entity, String.class);
        String jsonInput = response.getBody();
        TypeReference<HashMap<Integer, Integer>> typeRef
                = new TypeReference<>() {
        };
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonInput, typeRef);
    }

    private static void finishOrder(Map<Integer, Integer> orderedProducts) {
        orderedProducts.entrySet().parallelStream().forEach(x -> restTemplate.put(UPDATE_PRODUCTS_QUANTITY_API,
                new Product(x.getKey(), x.getValue() * -1)));
        System.out.println("order finished");
    }

    @RabbitListener(ackMode = "MANUAL",
            bindings = @QueueBinding(value = @Queue,
                    exchange = @Exchange("product-quantity-increased"),
                    key = "*"))
    public static void listenForProductQuantityIncrease() {
        System.out.println("A stock quantity increased");
    }


    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();
    private static HttpEntity entity = new HttpEntity<String>(headers);
    private static final String GET_PRODUCTS_QUANTITY_API = "http://localhost:8080/products/quantity";
    private static final String UPDATE_PRODUCTS_QUANTITY_API = "http://localhost:8080/products/quantity-update";
}
