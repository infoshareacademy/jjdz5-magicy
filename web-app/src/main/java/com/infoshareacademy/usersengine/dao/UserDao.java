package com.infoshareacademy.usersengine.dao;
import com.infoshareacademy.usersengine.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDao extends GenericDao<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByEmail(String email){
        final Query query = entityManager.createNamedQuery("findUserByEmail");
        query.setParameter("param", email);
        return (User) query.getSingleResult();
    }
}