package com.shopclient.controllers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.shopclient.beans.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddOrderController implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Map<Integer, Integer> orderedProducts = new HashMap<>();
        orderedProducts.put(1, 1);
        callPostNewOrderAPI(new Order(1, orderedProducts));
    }

    public static void main(String[] args) {
        Map<Integer, Integer> orderedProducts = new HashMap<>();
        orderedProducts.put(1, 1);
        callPostNewOrderAPI(new Order(1, orderedProducts));
    }

    public static void callPostNewOrderAPI(Order order) {
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        restTemplate.postForObject(POST_NEW_ORDER_API, order, Order.class);

        System.out.println(ORDER_ACCEPTED_MSG);
    }

    private static final String POST_NEW_ORDER_API = "http://localhost:8080/orders/add";
    private static final String ORDER_ACCEPTED_MSG = "Order accepted.";
    private static RestTemplate restTemplate = new RestTemplate();
    private static MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

}
