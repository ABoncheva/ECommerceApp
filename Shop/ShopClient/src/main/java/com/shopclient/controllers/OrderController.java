package com.shopclient.controllers;

import com.shopclient.beans.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class OrderController {

    public static void callPostNewOrderAPI(Order order) {
     Order newOrder = restTemplate.postForObject(POST_NEW_ORDER_API, order, Order.class);
     System.out.println("Order: " + order + " accepted");
    }

    private static final String POST_NEW_ORDER_API = "http://localhost:8080/orders/new-order";
    private static RestTemplate restTemplate = new RestTemplate();
}
