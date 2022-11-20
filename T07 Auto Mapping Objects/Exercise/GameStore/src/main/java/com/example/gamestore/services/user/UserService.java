package com.example.gamestore.services.user;

public interface UserService {
    String registerUser(String[] arg) throws IllegalAccessException;

    String login(String[] args);

    String logout();
}
