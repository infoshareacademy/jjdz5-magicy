package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.UserInput;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@Stateless
public class AdvertsValidationBean implements AdvertsValidation {
    UserInput userInput = new UserInput();

    public boolean checkDate(String date){
        LocalDate advertDate = LocalDate.parse(date);
        return LocalDate.now().isBefore(advertDate) && LocalDate.now().plusMonths(1).isAfter(advertDate);
    }
    public boolean askForStreet(HttpServletResponse resp, String street, String position) {
        if(street == null || street.isEmpty()|| !userInput.isStreetValid(street)){
            try {
                response(resp, "Enter correct "+position+" street");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean askForCity(HttpServletResponse resp, String city, String position) {
        if(city == null || city.isEmpty() ||!userInput.isCityValid(city)){
            try {
                response(resp, "Enter correct "+position+" city");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean askForDate(HttpServletResponse resp, String date) {
        if(date == null || date.isEmpty()|| !checkDate(date)){
            try {
                response(resp, "The entered date must be later than today and no later than a month after today. Enter correct date");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean askForTime(HttpServletResponse resp, String time, String position) {
        if(time == null || time.isEmpty()|| !userInput.isTimeValid(time)){
            try {
                response(resp, "Enter correct "+position+" time");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><body><form><t1>" + msg + "</t1><br/><input type=\"button\" value=\"Go back!\" onclick=\"history.back()\"></form></body></html>");
    }
}
