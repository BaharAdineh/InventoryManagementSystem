package com.challenge.ivms.userservice.repository;


import java.util.Optional;

import com.challenge.ivms.userservice.model.ERole;
import com.challenge.ivms.userservice.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}