package com.storeserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.storeserver.beans.ProductsManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class GetInsufficientProductsController {
    @RequestMapping(method = RequestMethod.GET, value="/products/insufficient-products", produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public static Map<Integer, Integer> getInsufficientProducts() throws JsonProcessingException {
         return ProductsManager.getInstance().getInsufficientProducts();
    }
}
