package com.storeserver.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.storeserver.beans.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
class GetProductsQuantityController {
    @RequestMapping(method = RequestMethod.GET, value = "/products/quantity", produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public static Map<Integer, Integer> getProductsQuantity() {
        return ProductsManager.getInstance().getProductsQuantity();
    }
}
