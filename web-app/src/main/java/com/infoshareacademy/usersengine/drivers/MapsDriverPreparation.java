package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.services.ParametersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsDriverPreparation {

    private static final Logger LOG = LoggerFactory.getLogger(MapsDriverPreparation.class);
    private DriversValidation driversValidation = new DriversValidationBean();
    private DriversManager driversManager = new DriversManagerBean();

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Inject
    private CarDao carDao;

    public MapsDriver driverMapReader(Map<String, String[]> map) {
        String name = ParametersService.getSpecificParameter(map, DriversConstants.PARAMETER_NAME);
        String surname = ParametersService.getSpecificParameter(map, DriversConstants.PARAMETER_SURNAME);
        String phone = ParametersService.getSpecificParameter(map, DriversConstants.PARAMETER_PHONE);
        String carModel = ParametersService.getSpecificParameter(map, DriversConstants.PARAMETER_CAR_MODEL);
        String licensePlate = ParametersService.getSpecificParameter(map, DriversConstants.PARAMETER_LICENSE_PLATE);
        Car car = new Car(licensePlate, carModel);
        return new MapsDriver(name, surname, phone, car);
    }

    public String validateDriver(MapsDriver driver) {
        String message = DriversConstants.EMPTY_FIELD;
        if (isPhoneNumberAlreadyInUse(driver.getPhoneNumber())) {
            LOG.info("Driver with phone number {} already exist.", driver.getPhoneNumber());
            message += DriversConstants.MESSAGE_DRIVER_ALREADY_EXIST;
            return message;
        }
        if (isLicensePlateAlreadyInUse(driver.getCar().getLicensePlate())) {
            message += "License plate is already in use.<br>";
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
        if (!driversValidation.askForNumber(driver.getPhoneNumber())) {
            LOG.info("Incorrect phone number from user input: {}.", driver.getPhoneNumber());
            message += DriversConstants.MESSAGE_INCORRECT_PHONE_NUMBER;
        }
        if (!driversValidation.askForTextNumbers(driver.getCar().getLicensePlate())) {
            message += "Enter correct license plate.<br>";
        }
        if (!driversValidation.askForTextNumbers(driver.getCar().getCarModel())) {
            message += "Enter correct car model.<br>";
        }
        return message;
    }

    public Driver getNewDriver(List<Driver> drivers, Driver driver) {
        driver.setId(driversManager.getNextDriverId(drivers));
        return driver;
    }

    private Boolean isPhoneNumberAlreadyInUse(String phoneNumber) {
        return mapsDriverDao.findAll().stream().anyMatch(d -> d.getPhoneNumber().equals(phoneNumber));
    }

    private Boolean isLicensePlateAlreadyInUse(String licensePlate) {
        return carDao.findAll().stream().anyMatch(c -> c.getLicensePlate().equals(licensePlate));
    }
}
