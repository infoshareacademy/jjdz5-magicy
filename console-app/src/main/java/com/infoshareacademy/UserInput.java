package com.infoshareacademy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class UserInput {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    Date askForDate(final String question) {
        String userDate = getUserInput(question).trim();
        while (!isDateValid(userDate)) {
            System.out.println("Entered date is incorrect, please try again.");
            userDate = getUserInput(question);
        }

        Date result = null;
        try {
            result = new SimpleDateFormat(DATE_FORMAT).parse(userDate);
        } catch (ParseException e) {
            System.out.println("Something went very wrong");
        }

        return result;
    }

    String askForTime(final String question) {
        String userTime = getUserInput(question).trim();
        while (!isTimeValid(userTime)) {
            System.out.println("Entered time is incorrect, please try again.");
            userTime = getUserInput(question);
        }

        return userTime;
    }

    String askForCity(final String question) {
        String userText = getUserInput(question).trim();
        while (!isCityValid(userText)) {
            System.out.println("Entered city is incorrect, please try again.");
            userText = getUserInput(question).trim();
        }
        return userText;
    }

    String askForStreet(final String question) {
        String userText = getUserInput(question).trim();
        while (!isStreetValid(userText)) {
            System.out.println("Entered street is incorrect, please try again.");
            userText = getUserInput(question).trim();
        }
        return userText;
    }

    String getUserInput(final String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + "> ");

        return scanner.nextLine();
    }

    public boolean isTimeValid(String time) {
        if (time.isEmpty()) {
            return false;
        }
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isCityValid(String city) {
        return city.matches("^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+$");
    }

    public boolean isInputValid(String input) {
        return input.matches("^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+$");
    }


    public boolean isStreetValid(String street) {
        return street.matches("^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż.]+[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż]*[0-9 /]*[a-z]*$");
    }

    public boolean isNumberValid(String number){
       return number.matches("^\\d{3}\\-?\\d{3}\\-?\\d{3}$");
    }

    Integer askForRating(final String question) {
        String userInput = getUserInput(question).trim();
        while(!userInput.matches("^?[0-5]")){
            System.out.println("You can give rate from 0 to 5");
            userInput = getUserInput(userInput);
        }
        Integer userRate = Integer.parseInt(userInput);
        return userRate;
    }

    Integer askForDriverId(final String question){
        String driverId = getUserInput(question).trim();
        DriverManager driverManager = new DriverManager();
        DriversList driversList = new DriversList();

        while(!driverId.matches("^?[0-"+driverManager.getMaxId(driversList.getDriversList())+"]")){
            System.out.println("Choose the driver's number:");
            driverId = getUserInput(driverId);
        }
        return Integer.parseInt(driverId);
    }
}
