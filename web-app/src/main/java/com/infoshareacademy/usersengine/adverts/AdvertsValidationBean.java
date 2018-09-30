package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.UserInput;

import javax.ejb.Stateful;
import java.time.LocalDate;

@Stateful
public class AdvertsValidationBean implements AdvertsValidation {
    UserInput userInput = new UserInput();

    public boolean checkDate(String date){
        LocalDate advertDate = LocalDate.parse(date);
        return LocalDate.now().isBefore(advertDate) && LocalDate.now().plusMonths(1).isAfter(advertDate);
    }

    public boolean askForStreet(String street) {
        return !(inputIsEmpty(street) || !userInput.isStreetValid(street));
    }

    public boolean askForCity(String city) {
        return !(inputIsEmpty(city) || !userInput.isCityValid(city));
    }

    public boolean askForDate(String date) {
      return !(inputIsEmpty(date) || !checkDate(date));
    }

    public boolean askForTime(String time) {
        return !(inputIsEmpty(time) || !userInput.isTimeValid(time));
    }

    private boolean inputIsEmpty(String input){
        return input==null || input.isEmpty();
    }
}
