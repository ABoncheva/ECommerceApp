package com.storeserver.controllers;

import com.storeserver.beans.*;
import com.storeserver.services.ProductsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class GetProductsController {
    @RequestMapping(method = RequestMethod.GET, value = "/products/all-products")
    @ResponseBody
    public Collection<Product> getProducts() {
        return productsManager.getProducts();
    }

    private final ProductsManager productsManager;
}
