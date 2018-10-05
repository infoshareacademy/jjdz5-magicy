package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import javax.ejb.Local;
import java.util.List;

@Local
public interface DriversValidation {
    boolean askForText(String text);
    boolean askForNumber(String number);
    boolean checkRating(String rating);
    boolean checkDriverId(List<Driver> drivers, String id);
    boolean askForRating(String rating);
    boolean askForDriverId(List<Driver> drivers, String id);
    String validateAdvertData(String id, String rating, List<Driver> drivers);
}
