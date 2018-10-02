package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;

import java.util.List;

public interface DriversManager {
    List<Driver> setNewRating(List<Driver> drivers, Integer id, Integer rating);
    void writeDriverData(List<Driver> drivers, String path);
}
