package com.infoshareacademy.usersengine.driver;

import com.infoshareacademy.Driver;

import java.util.List;

public interface DriverManager {
    List<Driver> addDriver(Driver driver, List<Driver> drivers);
    Integer getNextAdvertId(List<Driver> drivers);
    void driverToJson(List<Driver> driverList, String path);
}
