package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findUserByEmail(String email) {

        final Query query = entityManager.createQuery("SELECT u FROM Users WHERE userEmail ="+email+" LIMIT 1");

        return query.getResultList();
    }
}