package com.storeserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.storeserver.services.ProductsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GetInsufficientProductsController {
    @RequestMapping(method = RequestMethod.GET, value = "/products/insufficient-products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<Integer, Integer> getInsufficientProducts() {
        return productsManager.getInsufficientProducts();
    }

    private final ProductsManager productsManager;
}
