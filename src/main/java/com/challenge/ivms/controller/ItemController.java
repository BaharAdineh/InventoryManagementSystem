package com.challenge.ivms.controller;

import com.challenge.ivms.model.Item;
import com.challenge.ivms.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        final Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        final Item savedItem = itemRepository.save(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item item) {
        final Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            final Item updatedItem = existingItem.get();
            updatedItem.setName(item.getName());
            updatedItem.setQuantity(item.getQuantity());
            updatedItem.setPrice(item.getPrice());
            updatedItem.setLocation(item.getLocation());
            itemRepository.save(updatedItem);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable String id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

