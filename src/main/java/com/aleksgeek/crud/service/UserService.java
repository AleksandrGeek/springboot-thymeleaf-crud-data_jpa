package com.aleksgeek.crud.service;

import com.aleksgeek.crud.entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);
}
