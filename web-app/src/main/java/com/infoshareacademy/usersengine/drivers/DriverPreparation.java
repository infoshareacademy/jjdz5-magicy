package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import com.infoshareacademy.usersengine.services.PreparationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import java.util.List;
import java.util.Map;

@Stateful
public class DriverPreparation {

    private static final Logger LOG = LoggerFactory.getLogger(DriverPreparation.class);
    private Driver driver = new Driver();
    private DriversValidation driversValidation = new DriversValidationBean();
    private DriversManager driversManager = new DriversManagerBean();

    public Driver mapReader(Map<String, String[]> map) {
        String name = PreparationService.getSpecificParameter(map, DriversConstants.PARAMETER_NAME);
        String surname = PreparationService.getSpecificParameter(map, DriversConstants.PARAMETER_SURNAME);
        String phone = PreparationService.getSpecificParameter(map, DriversConstants.PARAMETER_PHONE);
        String city = PreparationService.getSpecificParameter(map, DriversConstants.PARAMETER_CITY);
        String district = PreparationService.getSpecificParameter(map, DriversConstants.PARAMETER_DISTRICT);

        driver.setName(name);
        driver.setSurname(surname);
        driver.setPhone(phone);
        driver.setCity(city);
        driver.setDistrict(district);
        driver.setRating(new Rating());

        return driver;
    }

    public String validateDriver(Driver driver, List<Driver> drivers) {
        String message = DriversConstants.EMPTY_FIELD;
        if (driversValidation.isPhoneNumberExist(driver.getPhone(), drivers)) {
            LOG.info("Driver with phone number {} already exist.", driver.getPhone());
            message += DriversConstants.MESSAGE_DRIVER_ALREADY_EXIST;
            return message;
        }
        if (!driversValidation.askForText(driver.getName())) {
            LOG.info("Incorrect name from user input: {}.", driver.getName());
            message += DriversConstants.MESSAGE_INCORRECT_NAME;
        }
        if (!driversValidation.askForText(driver.getSurname())) {
            LOG.info("Incorrect surname from user input: {}.", driver.getSurname());
            message += DriversConstants.MESSAGE_INCORRECT_SURNAME;
        }
        if (!driversValidation.askForNumber(driver.getPhone())) {
            LOG.info("Incorrect phone number from user input: {}.", driver.getPhone());
            message += DriversConstants.MESSAGE_INCORRECT_PHONE_NUMBER;
        }
        if (!driversValidation.askForText(driver.getCity())) {
            LOG.info("Incorrect city from user input: {}.", driver.getCity());
            message += DriversConstants.MESSAGE_INCORRECT_CITY;
        }
        if (!driversValidation.askForText(driver.getDistrict())) {
            LOG.info("Incorrect district from user input: {}.", driver.getDistrict());
            message += DriversConstants.MESSAGE_INCORRECT_DISTRICT;
        }
        return message;
    }

    public Driver getNewDriver(List<Driver> drivers) {
        driver.setId(driversManager.getNextDriverId(drivers));
        return driver;
    }
}
