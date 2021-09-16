package StoreServer.controllers;

import StoreServer.beans.Product;
import StoreServer.beans.ProductsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class GetAllProductsController {


    @RequestMapping(method = RequestMethod.GET, value = "/product/all_products")

    @ResponseBody
    public Collection<Product> getAllProducts() {
        return ProductsManager.getInstance().getAllProducts();
    }

}
