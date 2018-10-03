package com.infoshareacademy.usersengine.driver;

import com.infoshareacademy.Driver;

import java.util.List;

public class DriverManagerBean implements DriverManager {
    @Override
    public List<Driver> addDriver(Driver driver, List<Driver> drivers) {
        drivers.add(driver);
        return null;
    }

    @Override
    public Integer getNextDriverId(List<Driver> drivers) {

        return null;
    }

    @Override
    public void driverToJson(List<Driver> driverList, String path) {

    }
}
