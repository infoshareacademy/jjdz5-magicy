package com.infoshareacademy.usersengine.adverts;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Stateless
public class AdvertsValidationBean implements AdvertsValidation {

    public boolean checkStreet(String street){
        return street.matches("^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż.]+[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+[0-9 /]*[a-z]*$");
    }

    public boolean checkCity(String city){
        return city.matches("^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+$");
    }

    public boolean checkDate(String date){
        LocalDate advertDate = LocalDate.parse(date);
        return LocalDate.now().isBefore(advertDate);
    }
    public boolean askForStreet(HttpServletResponse resp, String street, String position) {
        if(!checkStreet(street)){
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
        if(!checkCity(city)){
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
        if(!checkDate(date)){
            try {
                response(resp, "The entered date is earlier than today. Enter correct date");
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
