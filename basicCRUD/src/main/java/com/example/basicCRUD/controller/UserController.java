package com.example.basicCRUD.controller;


import com.example.basicCRUD.exception.ResourceNotFoundException;
import com.example.basicCRUD.model.User;
import com.example.basicCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
        return HttpStatus.OK;
    }
}
