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
class UpdateProductQuantityController {
    @RequestMapping(method = RequestMethod.PUT, value="/products/quantity-update")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateProductQuantity(@RequestBody Product product) {
        productsManager.updateProductQuantity(product);
    }

    private final ProductsManager productsManager;
}
