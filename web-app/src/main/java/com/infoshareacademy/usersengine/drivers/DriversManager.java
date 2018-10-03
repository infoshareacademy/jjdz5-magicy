package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DriversManager {
    List<Driver> setNewRating(List<Driver> drivers, Integer id, Integer rating);
    void writeDriverData(List<Driver> drivers, String path);
}
