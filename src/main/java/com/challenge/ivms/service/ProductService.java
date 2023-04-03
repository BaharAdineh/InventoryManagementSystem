package com.challenge.ivms.service;

import com.challenge.ivms.errorhandling.ResourceNotFoundException;
import com.challenge.ivms.model.Product;
import com.challenge.ivms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(String productId) {
        final Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ResourceNotFoundException("Product not found with ID: " + productId);
        }
    }

    public Product updateProduct(String id, Product product) {
        final Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) {
        final Product product = getProductById(id);
        productRepository.delete(product);
    }
}

