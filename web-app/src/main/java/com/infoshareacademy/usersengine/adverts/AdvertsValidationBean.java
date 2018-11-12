package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.time.LocalDate;

@Stateless
public class AdvertsValidationBean implements AdvertsValidation {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertsValidationBean.class);
    private UserInput userInput = new UserInput();

    public boolean checkDate(String date){
        LOG.debug("Checking that date from user input ({}) is parsable.", date);
        LocalDate advertDate = LocalDate.parse(date);
        return LocalDate.now().isBefore(advertDate) && LocalDate.now().plusMonths(1).isAfter(advertDate);
    }

    public boolean askForStreet(String street) {
        LOG.debug("Validating street from user input ({}) and checking that it is not empty.", street);
        return !(inputIsEmpty(street) || !userInput.isStreetValid(street));
    }

    public boolean askForCity(String city) {
        LOG.debug("Validating city from user input ({}) and checking that it is not empty.", city);
        return !(inputIsEmpty(city) || !userInput.isCityValid(city));
    }

    public boolean askForDate(String date) {
        LOG.debug("Validating date from user input ({}) and checking that it is not empty.", date);
        return !(inputIsEmpty(date) || !checkDate(date));
    }

    public boolean askForTime(String time) {
        LOG.debug("Validating time from user input ({}) and checking that it is not empty.", time);
        return !(inputIsEmpty(time) || !userInput.isTimeValid(time));
    }

    private boolean inputIsEmpty(String input){
        return input==null || input.isEmpty();
    }
}