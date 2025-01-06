package com.v1.Jwt.controller;

import com.v1.Jwt.model.Users;
import com.v1.Jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        boolean isCreated = userService.saveUser(user);
        if (isCreated) {
            return ResponseEntity.ok("User Created Successfully");
        }
        else{
            return ResponseEntity.badRequest().body("User with the Email is already exist!");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // login
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
       return userService.verify(user);
    }

}
