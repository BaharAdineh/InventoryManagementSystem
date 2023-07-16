package com.challenge.ivms.userservice.controller;

import com.challenge.ivms.userservice.model.Role;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final MongoTemplate mongoTemplate;

    public RoleController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping
    public ResponseEntity<?> createDocument(@RequestBody Role role) {
        mongoTemplate.insert(role);
        return ResponseEntity.ok("Role created successfully");
    }
}