package StoreServer.controllers;

import StoreServer.beans.Product;
import StoreServer.beans.ProductsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddProductController {
    @RequestMapping(method = RequestMethod.POST, value="/products/add")

    @ResponseBody
    public void addProduct(@RequestBody Product product) {
        ProductsManager.getInstance().addProduct(product);
    }
}
