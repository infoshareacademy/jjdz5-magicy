package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface DriversManager {
    void writeDriverData(List<Driver> drivers, String path);
    Optional<Driver> getDriverById(List<Driver> drivers, Long id);
    List<Driver> updateDriversList(List<Driver> drivers, Integer rating, Long id);
    void addDriver(Driver driver, List<Driver> drivers);
    Long getNextDriverId(List<Driver> drivers);
}
