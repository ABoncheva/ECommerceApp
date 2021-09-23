package com.shopserver.controllers;

import com.shopserver.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.shopserver.beans.Order;

@RestController
@RequiredArgsConstructor
public class AddNewOrderController {

    @RequestMapping(method = RequestMethod.POST, value = "/orders/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(@RequestBody Order newOrder) {
        orderService.processOrder(newOrder);
    }

    private final OrderService orderService;
}
