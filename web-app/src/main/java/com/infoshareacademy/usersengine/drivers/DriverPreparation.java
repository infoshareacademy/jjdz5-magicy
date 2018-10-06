package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;

import javax.ejb.Stateful;
import java.util.List;
import java.util.Map;

@Stateful
public class DriverPreparation {
    Driver driver = new Driver();
    private DriversValidation driversValidation = new DriversValidationBean();
    private DriversManager driversManager = new DriversManagerBean();

    public Driver mapReader(Map<String, String[]> map){
        String name = map.get("name")[0].trim();
        String surname = map.get("surname")[0].trim();
        String phone = map.get("phone")[0].trim();
        String city = map.get("city")[0].trim();
        String district = map.get("district")[0].trim();

        driver.setName(name);
        driver.setSurname(surname);
        driver.setPhone(phone);
        driver.setCity(city);
        driver.setDistrict(district);
        driver.setRating(new Rating(0.0,0));

        return driver;
    }

    public String validateDriver(Driver driver){
        String message ="";

        if (!driversValidation.askForText(driver.getName())){
            message = message + "Enter correct name </br>";
        }
        if (!driversValidation.askForText(driver.getSurname())){
            message = message + "Enter correct Surname </br>";
        }
        if (!driversValidation.askForNumber(driver.getPhone())){
            message = message + "Enter correct phone number </br>";
        }
        if (!driversValidation.askForText(driver.getCity())){
            message = message + "Enter correct City <br>";
        }
        if (!driversValidation.askForText(driver.getDistrict())){
            message = message + "Enter correct district <br>";
        }

        return message;
    }

    public Driver getNewDriver(List<Driver> drivers){
        driver.setId(driversManager.getNextDriverId(drivers));
        return driver;
    }
}
