package com.challenge.ivms.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.challenge.ivms.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}

