package com.aleksgeek.crud.service;

import com.aleksgeek.crud.dao.UserDao;

import com.aleksgeek.crud.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {


    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void createUser(String name, String email) {
        validateNameAndEmail(name, email);

        User user = new User(name, email);
        user.setName(name);
        user.setEmail(email);
        userDao.save(user);
    }


    @Override
    public User getUserById(Long id) {
        validateId(id);

        User user = userDao.getById(id);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь с таким ID " + id
                    + " не найден");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void updateUser(Long id, String name, String email) {
        validateId(id);
        validateNameAndEmail(name, email);


        User user = userDao.getById(id);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь с таким ID " + id
                    + "не найден");
        }
        user.setName(name);
        user.setEmail(email);
        userDao.update(user);

    }

    @Override
    public void deleteUser(Long id) {
        validateId(id);
        userDao.delete(id);
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID должен быть положительным числом");
        }
    }

    private void validateNameAndEmail(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Не верный формат email");
        }
    }
}