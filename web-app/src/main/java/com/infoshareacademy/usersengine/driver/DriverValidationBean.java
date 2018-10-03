package com.infoshareacademy.usersengine.driver;

import com.infoshareacademy.UserInput;

public class DriverValidationBean implements DriverValidation {
    UserInput userInput = new UserInput();

    @Override
    public boolean askForText(String text) {
        return !(inputIsEmpty(text) || !userInput.isInputValid(text));
    }

    @Override
    public boolean askForNumber(String number) {
        return (inputIsEmpty(number) || !userInput.isNumberValid(number));
    }

    private boolean inputIsEmpty(String input){
        return input==null || input.isEmpty();
    }
}
