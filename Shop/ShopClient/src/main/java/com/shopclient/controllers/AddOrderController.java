package com.shopclient.controllers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.shopclient.beans.Order;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        restTemplate.postForObject(POST_NEW_ORDER_API, order, Order.class);

        System.out.println("Order accepted");
    }

    private static final String POST_NEW_ORDER_API = "http://localhost:8080/orders/add";
    private static RestTemplate restTemplate = new RestTemplate();
}
