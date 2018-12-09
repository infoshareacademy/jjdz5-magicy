package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.model.MapsAdvert;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class MapsAdvertDao extends GenericDao<MapsAdvert, Long> {

    public List<MapsAdvert> findAdvertsByDate(LocalDate date) {
        final Query query = entityManager.createQuery(
                "SELECT a FROM MapsAdvert a WHERE a.date = :requestDate");
        query.setParameter("requestDate", date);
        return query.getResultList();
    }
}
