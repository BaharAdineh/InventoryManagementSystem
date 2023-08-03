package com.challenge.ivms.productservice.repository;

import com.challenge.ivms.productservice.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
@Repository
public class ProductRepository extends MongoRepository<Product, String> {

}