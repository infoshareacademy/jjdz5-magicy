package com.infoshareacademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DriverManager {
    private  final UserInput userInput = new UserInput();
    private final ReadProperties readProperties = new ReadProperties();
    private String path = readProperties.readDriversList();

    public void showDrivers(List<Driver> driversList) {
        System.out.println("-------Drivers list-----------");
        for (Driver driver : driversList) {
            System.out.println(driver.getId() + " - " + driver.getName() + " " + driver.getSurname()
                    + ", " + driver.getCity() + ", " + driver.getRating().toString());
        }
        System.out.println("0 - Back");
        System.out.println("------------------------\n");
        int result = userInput.askForDriverId("Choose the driver you want to rate");
        if(result != 0){
            giveRating(driversList, result);
            writeDriverData(driversList);
        }

    }

    Long getMaxId(List<Driver> driversList){
        Long maxId = 2L;
        for(Driver driver: driversList){
            if(driver.getId()> maxId){
                maxId = driver.getId();
            }
        }
        return maxId;
    }

    public void giveRating(List<Driver> driversList, Integer driverNum){
        for(Driver driver: driversList){
            if(driverNum.equals(driver.getId())){

                driver.getRating().setAverage(driver.getRating().computeNewAverage(userInput.askForRating("Give your rating from 0 to 5")));
                driver.getRating().setPersons(driver.getRating().getPersons()+1);
                System.out.println("\n--------- New rating for "+driver.getName()+" ---------");
                System.out.println(driver.getId()+" - "+driver.getName()+" "+ driver.getSurname()
                        + ", "+ driver.getCity()+", rating: "+ driver.getRating().getAverage()+"/"+driver.getRating().getPersons());
                System.out.println("------------------------------\n");
                break;
            }
        }
    }

    public void writeDriverData(List<Driver> driversList){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(driversList);
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
