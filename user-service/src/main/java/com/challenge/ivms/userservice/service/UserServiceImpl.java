package com.challenge.ivms.userservice.service;

import com.challenge.ivms.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public Optional<User> getUserById(String userId) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(String id) {

    }
}
