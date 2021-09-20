package com.shopserver.controllers;

import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.shopserver.beans.Order;
import com.shopserver.beans.OrdersManager;

@Controller
public class AddNewOrderController {
    @RequestMapping(method = RequestMethod.POST, value = "/orders/add")//consumes = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public Order addOrder(@RequestBody Order newOrder) {
        OrdersManager.getInstance().addOrder(newOrder);

        return newOrder;
    }
}
