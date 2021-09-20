package com.storeserver.controllers;

import com.storeserver.beans.ProductsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class GetInsufficientProductsController {
    @RequestMapping(method = RequestMethod.GET, value="/products/insufficient-products")

    @ResponseBody
    public Map<Integer, Integer> getInsufficientProducts() {
         return ProductsManager.getInstance().getInsufficientProducts();
    }
}
