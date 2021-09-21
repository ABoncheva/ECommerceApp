package com.shopclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.shopclient")
public class ShopClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopClientApplication.class, args);
    }
}
