package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RatingDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer save(Rating r) {
        entityManager.persist(r);
        return r.getId();
    }

    public Rating update(Rating r) {
        return entityManager.merge(r);
    }

    public void delete(Long id) {
        final Rating r = entityManager.find(Rating.class, id);
        if (r!= null) {
            entityManager.remove(r);
        }
    }

    public Rating findById(Long id) {
        return entityManager.find(Rating.class, id);
    }

    public List<Rating> findAll() {
        final Query query = entityManager.createQuery("SELECT r FROM Rating r");

        return query.getResultList();
    }

}