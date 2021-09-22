package com.shopserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shopserver.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.shopserver.beans.Order;

@RestController
public class AddNewOrderController {
    @RequestMapping(method = RequestMethod.POST, value = "/orders/add", consumes = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public void addOrder(@RequestBody Order newOrder) throws JsonProcessingException {
        OrderService.processOrder(newOrder);
    }
}
