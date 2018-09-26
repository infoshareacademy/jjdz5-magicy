package com.infoshareacademy.usersengine.adverts;

import javax.ejb.Local;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
@Local
public interface AdvertsValidation {
    boolean checkDate(String date);
    boolean askForStreet(HttpServletResponse resp, String street, String position);
    boolean askForCity(HttpServletResponse resp, String street, String position);
    boolean askForDate(HttpServletResponse resp, String date);
    boolean askForTime(HttpServletResponse resp, String time, String position);
}
