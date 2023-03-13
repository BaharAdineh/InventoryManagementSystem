package com.challenge.ivms.repository;

import com.challenge.ivms.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findAll();

    Optional<Item> findById(String id);

    Item save(Item item);

    void deleteById(String id);
}
