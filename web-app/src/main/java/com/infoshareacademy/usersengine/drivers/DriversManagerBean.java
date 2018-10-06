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
    public Optional<Driver> getDriverById(List<Driver> drivers, Integer id){
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

    private Rating createNewRating(Driver d, Integer rating){
        d.getRating().setAverage(d.getRating().newAverage(rating));
        d.getRating().setPersons(d.getRating().getPersons()+1);
        return d.getRating();
    }
}
