package com.infoshareacademy.usersengine.driver;

import com.infoshareacademy.Driver;
import com.infoshareacademy.usersengine.adverts.AdvertData;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateful
public class DriverPreparation {
    Driver driver = new Driver();
    @Inject
    private DriverValidation driverValidation;
    @Inject
    private DriverManager driverManager;

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

        return driver;
    }

    public String validateDriver(Driver driver){
        String message ="";

        if (!driverValidation.askForText(driver.getName())){
            message = message + "Enter correct name";
        }
        if (!driverValidation.askForText(driver.getSurname())){
            message = message + "Enter correct Surname";
        }
        if (!driverValidation.askForNumber(driver.getPhone())){
            message = message + "Enter correct phone number";
        }
        if (!driverValidation.askForText(driver.getCity())){
            message = message + "Enter correct phone number";
        }
        if (!driverValidation.askForText(driver.getDistrict())){
            message = message + "Enter correct phone number";
        }

        return message;
    }

    public Driver getNewDriver(List<Driver> drivers){
        driver.setId(driverManager.getNextDriverId(drivers));
        return driver;
    }
}
