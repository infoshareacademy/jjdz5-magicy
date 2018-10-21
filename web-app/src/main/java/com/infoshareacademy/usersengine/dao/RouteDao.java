package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.Route;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RouteDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer save(Route r) {
        entityManager.persist(r);
        return r.getId();
    }

    public Route update(Route r) {
        return entityManager.merge(r);
    }

    public void delete(Long id) {
        final Route r = entityManager.find(Route.class, id);
        if (r!= null) {
            entityManager.remove(r);
        }
    }

    public Route findById(Long id) {
        return entityManager.find(Route.class, id);
    }

    public List<Route> findAll() {
        final Query query = entityManager.createQuery("SELECT r FROM Route r");

        return query.getResultList();
    }

}