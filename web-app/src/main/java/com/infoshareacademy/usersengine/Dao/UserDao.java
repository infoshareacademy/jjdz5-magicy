package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.User;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Stateless
public class UserDao extends GenericDao<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUsersEmails(String email){
        final Query query = entityManager.createNamedQuery("findUserByEmail");
        query.setParameter("param", email);
        return (User) query.getSingleResult();
    }
}