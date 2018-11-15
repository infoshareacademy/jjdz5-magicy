package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import javax.ejb.Local;
import java.util.List;

@Local
public interface DriversValidation {
    boolean askForText(String text);
    boolean askForNumber(String number);
    boolean checkIsDriverRatingValid(String rating);
    boolean checkIsDriverIdAlreadyUsed(List<Driver> drivers, String id);
    boolean askForRating(String rating);
    boolean askForDriverId(List<Driver> drivers, String id);
    String validateDriverData(String id, String rating, List<Driver> drivers);
    boolean isPhoneNumberExist(String number, List<Driver> drivers);
}
