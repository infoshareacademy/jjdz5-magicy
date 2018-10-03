package com.infoshareacademy.usersengine.drivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;

import javax.ejb.Stateless;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class DriversManagerBean implements DriversManager{

    public void writeDriverData(List<Driver> drivers, String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(drivers);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(arrayToJson);
            writer.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<Driver> getUserById(List<Driver> drivers, Integer id){
        return drivers.stream().filter(d -> d.getId().equals(id)).findAny();
    }

    public Rating setNewRating(Driver d, Integer rating){
        d.getRating().setAverage(d.getRating().newAverage(rating));
        d.getRating().setPersons(d.getRating().getPersons()+1);
        return d.getRating();
    }

    public List<Driver> updateDriversList(List<Driver> drivers, Rating rating, Integer id){
        for(Driver d: drivers){
            if(d.getId().equals(id)){
                d.setRating(rating);
            }
        }
        return drivers;
    }
}
