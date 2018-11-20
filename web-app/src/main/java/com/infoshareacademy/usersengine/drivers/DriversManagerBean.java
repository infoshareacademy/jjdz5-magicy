package com.infoshareacademy.usersengine.drivers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class DriversManagerBean implements DriversManager {

    private static final Logger LOG = LoggerFactory.getLogger(DriversManagerBean.class);
    private static final Integer START_ID = 0;
    private static final Integer VALUE_TO_ADD = 1;

    public void writeDriverData(List<Driver> drivers, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(drivers);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(arrayToJson);
            writer.close();
            LOG.info("Writing drivers to JSON file successful.");
        } catch (IOException e) {
            LOG.warn("IOException in writeDriverData method.");
        }
    }

    public Optional<Driver> getDriverById(List<Driver> drivers, Integer id) {
        LOG.debug("Searching specify driver by id: {}.", id);
        return drivers.stream().filter(d -> d.getId().equals(id)).findAny();
    }

    public List<Driver> updateDriversList(List<Driver> drivers, Integer rating, Integer id){
        for(Driver d: drivers){
            if(d.getId().equals(id)){
                d.setRating(createNewRating(d, rating));
            }
        }
        return drivers;
    }

    private Rating createNewRating(Driver d, Integer rating) {
        d.getRating().setAverage(d.getRating().computeNewAverage(rating));
        d.getRating().setPersons(d.getRating().getPersons() + VALUE_TO_ADD);
        LOG.debug("Added new rating ({}) for driver: {} {}.",
                rating, d.getName(), d.getSurname());
        return d.getRating();
    }

    public void addDriver(Driver driver, List<Driver> drivers) {
        drivers.add(driver);
    }

    public Long getNextDriverId(List<Driver> drivers) {
        Long nextDriverId = drivers.stream().mapToLong(Driver::getId).max()
                .orElse(START_ID) + VALUE_TO_ADD;
        LOG.debug("Next driver id value: {}.", nextDriverId);
        return nextDriverId;
    }
 }

