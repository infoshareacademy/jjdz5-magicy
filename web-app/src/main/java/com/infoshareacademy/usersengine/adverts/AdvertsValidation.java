package com.infoshareacademy.usersengine.adverts;

import javax.ejb.Local;

@Local
public interface AdvertsValidation {
    boolean checkDate(String date);
    boolean askForStreet(String street);
    boolean askForCity(String street);
    boolean askForDate(String date);
    boolean askForTime(String time);
}
