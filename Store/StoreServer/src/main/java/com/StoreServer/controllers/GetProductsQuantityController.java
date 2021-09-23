package com.storeserver.controllers;

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
class GetProductsQuantityController {
    @RequestMapping(method = RequestMethod.GET, value = "/products/quantity", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<Integer, Integer> getProductsQuantity() {
        return productsManager.getProductsQuantity();
    }

    private final ProductsManager productsManager;
}
