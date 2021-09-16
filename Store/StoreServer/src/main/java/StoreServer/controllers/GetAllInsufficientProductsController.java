package StoreServer.controllers;

import StoreServer.beans.ProductsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class GetAllInsufficientProductsController {

    @RequestMapping(method = RequestMethod.GET, value="/product/all_insufficient_products")

    @ResponseBody
    public Map<String, Integer> getAllInsufficientProducts() {
         return ProductsManager.getInstance().getAllInsufficientProducts();
    }

}
