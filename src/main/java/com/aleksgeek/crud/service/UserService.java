package com.aleksgeek.crud.service;

import com.aleksgeek.crud.entity.User;

import java.util.List;

public interface UserService {

    void createUser(String name, String email);

    User getUserById(Long id);

    List<User> getAllUsers();

    void updateUser(Long id, String name, String email);

    void deleteUser(Long id);
}
