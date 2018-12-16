package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class DriversValidationBean implements DriversValidation{

    private static final Logger LOG = LoggerFactory.getLogger(DriversValidationBean.class);
    private static final String RATING_REGEX = "^?[1-5]";
    private static final String WHITESPACE = " ";

    private UserInput userInput = new UserInput();

    public String validateDriverData(String id, String rating, List<Driver> drivers){
        String message = DriversConstants.EMPTY_FIELD;
        if(askForDriverId(drivers, id)){
            message += DriversConstants.MESSAGE_SOMETHING_WENT_WRONG;
        }
        if(!askForRating(rating)){
            message += DriversConstants.MESSAGE_ADD_RATING_USING_ALERT;
        }
        return message;
    }

    public boolean askForRating(String rating) {
        return !(isInputEmpty(rating) || !checkIsDriverRatingValid(rating));
    }

    public boolean askForDriverId(List<Driver> drivers, String id) {
        return !(isInputEmpty(id) || !checkIsDriverIdAlreadyUsed(drivers, id));
    }

    public boolean checkIsDriverRatingValid(String rating) {
        LOG.debug("Checking rating: {}.", rating);
        return rating.matches(RATING_REGEX);
    }

    public boolean checkIsDriverIdAlreadyUsed(List<Driver> drivers, String id){
        LOG.debug("Checking id: {}.", id);
        return drivers.stream().anyMatch(driver -> driver.getId().equals(Long.parseLong(id)));
    }

    public boolean askForText(String text) {
        return !(isInputEmpty(text) || !userInput.isInputValid(text));
    }

    public boolean askForTextNumbers(String text) {
        return !(isInputEmpty(text) || !userInput.isInputTextNumberValid(text));
    }

    public boolean askForNumber(String number) {
        return !(isInputEmpty(number) || !userInput.isNumberValid(number));
    }

    public boolean isPhoneNumberExist(String number, List<Driver> drivers){

        String newNumber = removeWhiteSigns(number);

        for (Driver driver: drivers){
            String driversPhoneNumber = removeWhiteSigns(driver.getPhone());
            if(newNumber.equals(driversPhoneNumber)){
                return true;
            }
        }
        return false;
    }

    private boolean isInputEmpty(String input){
        return input==null || input.isEmpty();
    }

    private String removeWhiteSigns(String text){
        text = text.replaceAll(WHITESPACE, DriversConstants.EMPTY_FIELD);
        return text;
    }
}