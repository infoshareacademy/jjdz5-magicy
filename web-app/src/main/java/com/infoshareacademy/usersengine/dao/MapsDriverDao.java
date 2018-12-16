package com.infoshareacademy.usersengine.dao;

import com.infoshareacademy.usersengine.model.MapsDriver;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class MapsDriverDao extends GenericDao<MapsDriver, Long> {

    public void deleteDriverAdvert(MapsDriver driverId) {
        final Query query = entityManager.createQuery(
                "DELETE FROM MapsAdvert m WHERE m.driver = :driverId");
        query.setParameter("driverId", driverId);
        query.executeUpdate();
    }

    public void deleteDriverUser(MapsDriver driverId) {
        final Query query = entityManager.createQuery(
                "DELETE FROM User m WHERE m.driver = :driverId");
        query.setParameter("driverId", driverId);
        query.executeUpdate();
    }

}