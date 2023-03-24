package com.challenge.ivms.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.challenge.ivms.model.Inventory;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, Long> {

    Optional<Inventory> findByItemId(Long itemId);

}

