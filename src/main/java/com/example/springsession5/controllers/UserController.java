package com.example.springsession5.controllers;

// UserController.java
import com.example.springsession5.models.User;
import com.example.springsession5.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String search) {
        List<User> allUsers = userService.findAllUsers();

        // Sử dụng Stream API để lọc theo username
        if (search != null && !search.isEmpty()) {
            List<User> filteredUsers = allUsers.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(search.toLowerCase()))
                    .toList();
            return ResponseEntity.ok(filteredUsers); // Trả về 200 OK
        }

        return ResponseEntity.status(200).body(allUsers);
    }
}