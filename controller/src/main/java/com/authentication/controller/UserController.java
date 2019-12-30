package com.authentication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.controller.exception.ResourceNotFoundException;
import com.authentication.model.User;
import com.authentication.repository.IUserRepository;
import com.authentication.service.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserRepository userService;

    @Autowired
    private IUserService test;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return test.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException {

        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setUpdatedAt(new Date());
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userService.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
