package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.model.AdvertPartType;
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
    
    public List<MapsAdvert> findAdvertsByStartCity(String city) {
        return findAdvertsByCity(city, AdvertPartType.START);
    }
    
    public List<MapsAdvert> findAdvertsByEndCity(String city) {
        return findAdvertsByCity(city, AdvertPartType.END);
    }

    private List<MapsAdvert> findAdvertsByCity(String city, AdvertPartType type) {
        final Query query = entityManager.createQuery(
                "SELECT adv FROM MapsAdvert adv LEFT JOIN MapsAddress adr " +
                        "ON adv." + type.getAddressVariable() + ".mapsPointId = adr.mapsPointId " +
                        "WHERE adr.city = :requestCity");
        query.setParameter("requestCity", city);
        return query.getResultList();
    }
}
