package com.shopserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shopserver.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.shopserver.beans.Order;

@Controller
public class AddNewOrderController {
    @RequestMapping(method = RequestMethod.POST, value = "/orders/add")

    @ResponseBody
    public void addOrder(@RequestBody Order newOrder) throws JsonProcessingException {
        orderService.processOrder(newOrder);
    }

    private OrderService orderService;
}
