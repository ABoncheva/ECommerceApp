package com.shopserver.controllers;

import com.shopserver.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class GetRequestedProductsQuantityController {
    @RequestMapping(method = RequestMethod.GET, value = "/products/requested", produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public Map<Integer, Integer> getRequestedProductsQuantity()  {
        return OrderService.getRequestedProducts();
    }
}
