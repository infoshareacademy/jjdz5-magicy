package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.UserInput;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class DriversValidationBean implements DriversValidation{

    private static final String ratingRegex = "^?[1-5]";
    UserInput userInput = new UserInput();

    public boolean checkRating(String rating) {
        return rating.matches(ratingRegex);
    }

    public boolean checkDriverId(List<Driver> drivers, String id){
        for(Driver d: drivers){
            if(d.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean askForRating(String rating) {
        return !(inputIsEmpty(rating) || !checkRating(rating));
    }

    public boolean askForDriverId(List<Driver> drivers, String id) {
        return !(inputIsEmpty(id) || !checkDriverId(drivers, id));
    }

    public String validateAdvertData(String id, String rating, List<Driver> drivers){
        String message = "";
        if(askForDriverId(drivers, id)){
            message = message + "Ups! Something went wrong :( Try again";
        }
        if(!askForRating(rating)){
            message = message + "Ups! Something went wrong. Add rating using alert";
        }
        return message;
    }

    private boolean inputIsEmpty(String input){
        return input==null || input.isEmpty();
    }

    public boolean askForText(String text) {
        return !(inputIsEmpty(text) || !userInput.isInputValid(text));
    }

    public boolean askForNumber(String number) {
        return (inputIsEmpty(number) || userInput.isNumberValid(number));
    }
}
