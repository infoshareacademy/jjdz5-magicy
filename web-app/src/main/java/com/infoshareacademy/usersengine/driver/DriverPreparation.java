package com.infoshareacademy.usersengine.driver;

import com.infoshareacademy.Driver;
import com.infoshareacademy.usersengine.adverts.AdvertData;

import java.util.Map;

public class DriverPreparation {
    Driver driver = new Driver();

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
}
