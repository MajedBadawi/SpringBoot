package com.example.basicCRUD.service;

import com.example.basicCRUD.exception.ResourceNotFoundException;
import com.example.basicCRUD.model.User;
import com.example.basicCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
            return user.get();
        else
            throw new ResourceNotFoundException("User not found with id: " + userId);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int userId, User userDetails) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User new_user = user.get();
            new_user.setId(userId);
            new_user.setFirstName(userDetails.getFirstName());
            new_user.setLastName(userDetails.getLastName());
            new_user.setEmailId(userDetails.getEmailId());
            new_user.setCreatedBy(userDetails.getCreatedBy());
            new_user.setCreatedAt(userDetails.getCreatedAt());
            new_user.setUpdatedBy(userDetails.getUpdatedBy());
            new_user.setUpdatedAt(new Date());
            userRepository.save(new_user);
            return new_user;
        } else
            throw new ResourceNotFoundException("User not found with id: " + userId);
    }

    @Override
    public void deleteUser(int userId) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
            userRepository.delete(user.get());
        else
            throw new ResourceNotFoundException("User not found with id: " + userId);
    }
}
