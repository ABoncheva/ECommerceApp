package com.storeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.storeserver")
public class StoreServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreServerApplication.class, args);
    }
}
