package com.aleksgeek.crud.dao;

import com.aleksgeek.crud.entity.User;

import java.util.List;

public interface UserDao {

    //CRUD - create, read, update, delete

    // CREATE
    void save(User user);

    //READ/FIND
    User getById(Long id);

    List<User> getAll();

    //UPDATE
    void update(User user);

    // DELETE
    void delete(Long id);

}
