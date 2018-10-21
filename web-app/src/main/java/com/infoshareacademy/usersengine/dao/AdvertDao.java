package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.Advert;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AdvertDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer save(Advert a) {
        entityManager.persist(a);
        return a.getId();
    }

    public Advert update(Advert a) {
        return entityManager.merge(a);
    }

    public void delete(Long id) {
        final Advert a = entityManager.find(Advert.class, id);
        if (a != null) {
            entityManager.remove(a);
        }
    }

    public Advert findById(Long id) {
        return entityManager.find(Advert.class, id);
    }

    public List<Advert> findAll() {
        final Query query = entityManager.createQuery("SELECT a FROM Advert a");

        return query.getResultList();
    }

}