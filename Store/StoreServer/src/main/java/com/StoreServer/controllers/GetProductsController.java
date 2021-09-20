package com.storeserver.controllers;

import com.storeserver.beans.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class GetProductsController {
    @RequestMapping(method = RequestMethod.GET, value = "/products/all-products")

    @ResponseBody
    public Collection<Product> getProducts() {
        return ProductsManager.getInstance().getProducts();
    }
}
