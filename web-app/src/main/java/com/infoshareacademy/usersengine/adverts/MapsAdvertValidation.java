package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.UserInput;
import com.infoshareacademy.usersengine.model.AddressType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MapsAdvertValidation {

    private static final Logger LOG = LoggerFactory.getLogger(MapsAdvertValidation.class);
    private MapsAdvertParametersPreparation preparation;
    private AdvertsValidation advertsValidation = new AdvertsValidationBean();
    private UserInput userInput = new UserInput();
    private List<String> checkingMessages;

    public void checkAdvertDataCorrection(AdvertData advertData) {
        checkingMessages = new ArrayList<>();

    }

    private void checkCityCorrection(String city, AddressType type) {
        if (!advertsValidation.askForCity(city)) {
            LOG.info("Incorrect {} city from user input: {}.", type.name().toLowerCase(), city);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_CITY,
                    AdvertsConstants.MESSAGE_INCORRECT_END_CITY);
        }
    }

    private void checkStreetCorrection(String street, AddressType type) {
        if (!advertsValidation.askForStreet(street)) {
            LOG.info("Incorrect {} street from user input: {}.", type.name().toLowerCase(), street);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_STREET,
                    AdvertsConstants.MESSAGE_INCORRECT_END_STREET);
        }
    }

    private void checkStreetNumberCorrection(String streetNumber, AddressType type) {
        if (!userInput.isStreetNumberValid(streetNumber) || inputIsEmpty(streetNumber)) {
            LOG.info("Incorrect {} street number from user input: {}.",
                    type.name().toLowerCase(), streetNumber);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_STREET_NUMBER,
                    AdvertsConstants.MESSAGE_INCORRECT_END_STREET_NUMBER);
        }
    }

    private void selectMessage(AddressType type, String startDataMessage, String endDataMessage) {
        if (type.equals(AddressType.START)) {
            checkingMessages.add(startDataMessage);
        }
        if (type.equals(AddressType.END)) {
            checkingMessages.add(endDataMessage);
        }
    }

    private Boolean inputIsEmpty(String input){
        return input==null || input.isEmpty();
    }

}
