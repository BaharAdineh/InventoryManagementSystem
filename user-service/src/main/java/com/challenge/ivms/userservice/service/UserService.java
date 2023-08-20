package com.challenge.ivms.userservice.service;

import com.challenge.ivms.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<User> getUserById(String userId);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);
}