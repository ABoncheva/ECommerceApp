package com.StoreServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com/StoreServer"})
public class StoreClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreClientApplication.class, args);
    }
}
