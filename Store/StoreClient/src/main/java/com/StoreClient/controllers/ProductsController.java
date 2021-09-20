package com.StoreClient.controllers;

import com.StoreClient.beans.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class ProductsController {

    // to be removed
    private static void callGetAllProductsAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<String>(headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_PRODUCTS_API, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }


    // id and to be updated to be passes as arguments
    private static void callUpdateProductQuantityAPI() {
        Product updateProduct = new Product(1, 10);
        restTemplate.put(UPDATE_PRODUCT_QUANTITY_API, updateProduct);
    }

    private static void callGetInsufficientProductsAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<String>(headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_INSUFFICIENT_PRODUCTS_API, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }


    private static final String GET_ALL_PRODUCTS_API = "http://localhost:8080/products/all-products";
    private static final String UPDATE_PRODUCT_QUANTITY_API = "http://localhost:8080/products/quantity-update";
    private static final String GET_INSUFFICIENT_PRODUCTS_API = "http://localhost:8080/products/insufficient-products";
    private static RestTemplate restTemplate = new RestTemplate();
}
