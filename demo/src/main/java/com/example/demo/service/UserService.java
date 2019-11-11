package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    User createUser(User user);
    User updateUser(int userId, User user) throws ResourceNotFoundException;
    void deleteUser(int userId) throws ResourceNotFoundException;
}
