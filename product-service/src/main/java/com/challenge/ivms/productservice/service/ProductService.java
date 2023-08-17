package com.challenge.ivms.productservice.service;

import com.challenge.ivms.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(String id, Product product) ;

    void deleteProduct(String id);

    Optional<Product> getProductById(String productId);
}
