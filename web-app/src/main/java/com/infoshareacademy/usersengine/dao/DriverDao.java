package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.Driver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DriverDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer save(Driver d) {
        entityManager.persist(d);
        return d.getId();
    }

    public Driver update(Driver d) {
        return entityManager.merge(d);
    }

    public void delete(Long id) {
        final Driver d = entityManager.find(Driver.class, id);
        if (d != null) {
            entityManager.remove(d);
        }
    }

    public Driver findById(Long id) {
        return entityManager.find(Driver.class, id);
    }

    public List<Driver> findAll() {
        final Query query = entityManager.createQuery("SELECT d FROM Driver d");

        return query.getResultList();
    }

}