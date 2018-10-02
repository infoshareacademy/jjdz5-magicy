package com.infoshareacademy.usersengine.drivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Driver;

import javax.ejb.Stateless;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Stateless
public class DriversManagerBean implements DriversManager{

    public List<Driver> setNewRating(List<Driver> drivers, Integer id, Integer rating){
        for(Driver d: drivers){
            if(d.getId().equals(id)){
                d.getRating().setAverage(d.getRating().newAverage(rating));
                d.getRating().setPersons(d.getRating().getPersons()+1);
            }
        }
        return drivers;
    }

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
}
