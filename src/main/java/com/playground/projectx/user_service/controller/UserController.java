package com.playground.projectx.user_service.controller;

import com.playground.projectx.user_service.model.User;
import com.playground.projectx.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
public class UserController {

    @Autowired
    UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("user created");
    }

    @PostMapping("/login1")
    public String login(@RequestBody User user) {
        Optional<User> savedUser = userRepository.findById(user.getId());
        if (savedUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (user.getPassword().equals(savedUser.get().getPassword())) {
            return "logged in";
        }
        return "incorrect Cred";
    }

    @PostMapping("/logout")
    public String logout(@RequestBody User user) {
        return "logout";
    }

    @GetMapping
    public String getAllUsers() {/**/
        return "All users are here test";
    }
}
