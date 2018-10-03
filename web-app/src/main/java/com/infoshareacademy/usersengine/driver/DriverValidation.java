package com.infoshareacademy.usersengine.driver;

import javax.ejb.Local;

@Local
public interface DriverValidation {
    boolean askForText(String text);
    boolean askForNumber(String number);
}
