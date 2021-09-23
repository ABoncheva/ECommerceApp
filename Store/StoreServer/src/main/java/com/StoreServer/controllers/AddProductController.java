package com.storeserver.controllers;

import com.storeserver.beans.*;
import com.storeserver.services.ProductsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class AddProductController {
    @RequestMapping(method = RequestMethod.POST, value="/products/add")
    @ResponseStatus(code= HttpStatus.OK)
    public void addProduct(@RequestBody Product product) {
        System.out.println("Adding product");
        productsManager.addProduct(product);
    }

    private final ProductsManager productsManager;
}
