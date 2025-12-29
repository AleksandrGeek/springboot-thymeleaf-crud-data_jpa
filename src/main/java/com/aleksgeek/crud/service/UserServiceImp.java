package com.aleksgeek.crud.service;

import com.aleksgeek.crud.dao.UserDao;

import com.aleksgeek.crud.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {


    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void createUser(User user) {
        validateUser(user);
        userDao.save(user);
    }


    @Override
    public User getUserById(Long id) {
        validateId(id);

        return Optional.ofNullable(userDao.getById(id))
                .orElseThrow(() ->
                        new IllegalArgumentException("Пользователь с ID " + id
                                + " не найден"));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void updateUser(User user) {
        validateUser(user);
        validateId(user.getId());


        if (userDao.getById(user.getId()) == null) {
            throw new IllegalArgumentException("Пользователь с ID " + user.getId() + " не найден");
        }
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

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не может быть null");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if (!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Неверный формат email");
        }
    }
}