package com.infoshareacademy.usersengine.Dao;

import com.infoshareacademy.usersengine.statistics.UserStatistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserStatisticDao {

    @PersistenceContext
    private EntityManager entityManager;

    public String save(UserStatistic userStatistic){
        entityManager.persist(userStatistic);
        return userStatistic.getUserEmail();
    }

    public List<UserStatistic> findAll(){
        final Query query = entityManager.createQuery("SELECT ua FROM UserStatistic ua ");
        return query.getResultList();
    }

    public UserStatistic findByEmail(String userEmail){
        return entityManager.find(UserStatistic.class, userEmail);
    }

}
