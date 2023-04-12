package com.ivms.userservice.service;
import com.ivms.userservice.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
    User getUserByUsername(String username);
}

