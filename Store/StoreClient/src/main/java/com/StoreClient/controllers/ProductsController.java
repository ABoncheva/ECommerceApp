package com.storeclient.controllers;

import com.storeclient.beans.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class ProductsController {

    public static void main(String[] args) {
      //  callAddProductAPI();
        callUpdateProductQuantityAPI();
    }

    private static void callGetAllProductsAPI() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_PRODUCTS_API, HttpMethod.GET, entity, String.class);
    }

    // Product to be passed as parameter, hardcoded for the demo
    private static void callUpdateProductQuantityAPI() {
        Product updateProduct = new Product(2, 3);
        restTemplate.put(UPDATE_PRODUCT_QUANTITY_API, updateProduct);
    }

    private static void callGetInsufficientProductsAPI() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.exchange(GET_INSUFFICIENT_PRODUCTS_API, HttpMethod.GET, entity, String.class);
        // left here because of the demo
        System.out.println(result);
    }

    private static void callAddProductAPI() {
        Product newProduct = new Product(2, 2);
        restTemplate.postForObject(ADD_PRODUCT_API, newProduct, Product.class);
    }

    private static final String GET_ALL_PRODUCTS_API = "http://localhost:8080/products/all-products";
    private static final String UPDATE_PRODUCT_QUANTITY_API = "http://localhost:8080/products/quantity-update";
    private static final String GET_INSUFFICIENT_PRODUCTS_API = "http://localhost:8080/products/insufficient-products";
    private static final String ADD_PRODUCT_API = "http://localhost:8080/products/add";
    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();
    private static HttpEntity entity = new HttpEntity<String>(headers);
}
