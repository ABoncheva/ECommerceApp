package com.storeserver.controllers;

import com.storeserver.beans.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class UpdateProductQuantityController {
    @RequestMapping(method = RequestMethod.PUT, value="/products/quantity-update")

    @ResponseBody
    public void updateProductQuantity(@RequestBody Product product) {
        ProductsManager.getInstance().updateProductQuantity(product);
    }
}
