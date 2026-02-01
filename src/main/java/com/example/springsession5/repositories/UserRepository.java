package com.example.springsession5.repositories;

import com.example.springsession5.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>(Arrays.asList(
            new User(1L, "admin", "admin@gmail.com", "ADMIN"),
            new User(2L, "huong_giang", "giang@gmail.com", "USER"),
            new User(3L, "minh_quan", "quan@gmail.com", "USER")
    ));

    public List<User> findAll() { return users; }

    public User findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}