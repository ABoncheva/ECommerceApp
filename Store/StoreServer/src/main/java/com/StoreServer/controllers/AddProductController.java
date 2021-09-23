package com.storeserver.controllers;

import com.storeserver.beans.*;
import com.storeserver.services.ProductsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class AddProductController {
    @RequestMapping(method = RequestMethod.POST, value="/products/add")
    public void addProduct(@RequestBody Product product) {
        productsManager.addProduct(product);
    }

    private final ProductsManager productsManager;
}
