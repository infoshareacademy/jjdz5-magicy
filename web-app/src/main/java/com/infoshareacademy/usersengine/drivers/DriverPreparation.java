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
        String message = PreparationService.EMPTY_MESSAGE;
        if (driversValidation.isPhoneNumberExist(driver.getPhone(), drivers)) {
            LOG.info("Driver with this phone number already exist.");
            message += DriversConstants.MESSAGE_DRIVER_ALREADY_EXIST;
            return message;
        }
        if (!driversValidation.askForText(driver.getName())) {
            LOG.info("Name from user input is incorrect.");
            message += DriversConstants.MESSAGE_INCORRECT_NAME;
        }
        if (!driversValidation.askForText(driver.getSurname())) {
            LOG.info("Surname from user input is incorrect.");
            message += DriversConstants.MESSAGE_INCORRECT_SURNAME;
        }
        if (!driversValidation.askForNumber(driver.getPhone())) {
            LOG.info("Phone number from user input is incorrect.");
            message += DriversConstants.MESSAGE_INCORRECT_PHONE_NUMBER;
        }
        if (!driversValidation.askForText(driver.getCity())) {
            LOG.info("City from user input is incorrect.");
            message += DriversConstants.MESSAGE_INCORRECT_CITY;
        }
        if (!driversValidation.askForText(driver.getDistrict())) {
            LOG.info("District from user input is incorrect.");
            message += DriversConstants.MESSAGE_INCORRECT_DISTRICT;
        }
        return message;
    }

    public Driver getNewDriver(List<Driver> drivers) {
        driver.setId(driversManager.getNextDriverId(drivers));
        return driver;
    }
}
