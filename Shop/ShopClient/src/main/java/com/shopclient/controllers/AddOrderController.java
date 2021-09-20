package com.shopclient.controllers;

import com.shopclient.beans.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AddOrderController {
    public static void main(String[] args) {
        Map<Integer, Integer> orderedProducts = new HashMap<>();
        orderedProducts.put(1, 1);
        Order order = new Order(1, orderedProducts);
        callPostNewOrderAPI(new Order(1, orderedProducts));
    }

    public static void callPostNewOrderAPI(Order order) {
        //restTemplate.postForObject(POST_NEW_ORDER_API, order, Order.class);
        restTemplate.put(POST_NEW_ORDER_API, order);
        System.out.println("Order accepted");
    }

    private static final String POST_NEW_ORDER_API = "http://localhost:8080/orders/add";
    private static RestTemplate restTemplate = new RestTemplate();
}
