package com.infoshareacademy.usersengine.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Driver;

import javax.ejb.Stateful;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Stateful
public class DriverManagerBean implements DriverManager {
    @Override
    public List<Driver> addDriver(Driver driver, List<Driver> drivers) {
        drivers.add(driver);
        return drivers;
    }

    @Override
    public Integer getNextDriverId(List<Driver> drivers) {
        Integer idMax = 0;
        for (Driver driver: drivers
             ) {
            if (driver.getId() > idMax){
                idMax = driver.getId();
            }

        }

        return idMax+1;
    }

    @Override
    public void driverToJson(List<Driver> driverList, String path) {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(driverList);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(arrayToJson);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

