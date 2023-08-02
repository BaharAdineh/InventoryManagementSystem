package com.challenge.ivms.productservice.repository;

import com.challenge.ivms.productservice.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends MongoRepository<Product, String> {

}