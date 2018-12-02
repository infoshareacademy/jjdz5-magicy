package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.statistics.UserStatistic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserStatisticDao {

    @PersistenceContext
    private EntityManager entityManager;

    public long save(UserStatistic userStatistic) {
        entityManager.persist(userStatistic);
        return userStatistic.getId();
    }

        public List<UserStatistic> findAll() {
            final Query query = entityManager.createQuery("SELECT us FROM UserStatistic us ");
            return query.getResultList();
        }

        public UserStatistic findById ( long id){
            return entityManager.find(UserStatistic.class, id);
        }

    }

