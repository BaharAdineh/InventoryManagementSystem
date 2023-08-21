package com.challenge.ivms.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.challenge.ivms")
public class OrderService {

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }

}
