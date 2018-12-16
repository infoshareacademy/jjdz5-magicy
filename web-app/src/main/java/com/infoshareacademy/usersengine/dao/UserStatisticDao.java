package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.statistics.UserStatistic;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class UserStatisticDao extends GenericDao<UserStatistic, Long>{

    public List<UserStatistic> findStatisticInInterval(LocalDateTime date1, LocalDateTime date2) {
        final Query query = entityManager.createQuery(
                "SELECT s FROM UserStatistic s WHERE s.time >= :date1 and s.time <= :date2");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }
}

