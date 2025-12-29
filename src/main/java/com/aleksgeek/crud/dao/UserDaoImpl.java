package com.aleksgeek.crud.dao;

import com.aleksgeek.crud.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(User user) {
      entityManager.persist(user);

    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        String jpql = "SELECT u FROM User u ORDER BY u.id";
        //TypedQuery<User> - типо_безопасный запрос
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        //getResultList - метод для получения списка
        return query.getResultList();
    }

    @Override
    public void update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("Cannot update user without ID");
        }
        //присоедини объект
       entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        //находим объект
        User user = getById(id);
        if (user != null) {
            // Удаление из контекста(persistence)
            entityManager.remove(user);
        }
    }
}


