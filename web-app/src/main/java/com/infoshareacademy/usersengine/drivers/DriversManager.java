package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface DriversManager {
    Rating setNewRating(Driver d, Integer rating);
    void writeDriverData(List<Driver> drivers, String path);
    Optional<Driver> getUserById(List<Driver> drivers, Integer id);
    List<Driver> updateDriversList(List<Driver> drivers, Rating rating, Integer id);
}
