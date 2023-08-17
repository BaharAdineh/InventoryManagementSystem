package com.challenge.ivms.productservice;

import com.challenge.ivms.productservice.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProductService {

	public static void main(String[] args) {
		SpringApplication.run(ProductService.class, args);
	}
}
