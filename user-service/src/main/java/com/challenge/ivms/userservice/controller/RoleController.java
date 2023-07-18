package com.challenge.ivms.userservice.controller;

import com.challenge.ivms.userservice.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/roles")
public class RoleController {

       /* @PostMapping
        public ResponseEntity<Role> createRole(@RequestBody Role role) {
            log.info("Creating role: {}", role);
            return ResponseEntity.ok(role);
        }*/
}