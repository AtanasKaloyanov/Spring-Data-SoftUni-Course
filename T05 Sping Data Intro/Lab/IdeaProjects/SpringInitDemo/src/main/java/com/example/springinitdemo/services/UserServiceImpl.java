package com.example.springinitdemo.services;

import com.example.springinitdemo.models.Account;
import com.example.springinitdemo.models.User;
import com.example.springinitdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, int age) {
        // Validate username and age

        if (username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed");
        }

        // Check if the username is unique (ob the username is arleady in the database)
       Optional<User> searchingUsername = this.userRepository.findByUsername(username);

        if (searchingUsername.isPresent()) {
            throw new RuntimeException("Username is already in use");
        }

        // Add default account
        Account account = new Account();
        User user = new User(username, age, account);

        // Save user
        this.userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }
}
