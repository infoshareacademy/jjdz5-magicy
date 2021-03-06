package com.infoshareacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class UserInput {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String CITY_REGEX = "^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+$";
    private static final String INPUT_REGEX = "^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+$";
    private static final String INPUT_TEXT_NUMBER_REGEX = "^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]*[0-9 /]+$";
    private static final String STREET_REGEX = "^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż.]+[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż]*[0-9 /]*[a-z]*$";
    private static final String STREET_NAME_REGEX = "^[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+[a-zA-Z ĄąĆćĘęŁłŃńÓóŚśŹźŻż]*$";
    private static final String STREET_NUMBER_REGEX = "^+[0-9 /]*[a-zA-Z]*$";
    private static final String NUMBER_REGEX = "^\\d{3}\\ \\d{3}\\ \\d{3}$";

    private Logger LOG = LoggerFactory.getLogger(UserInput.class);

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
            LOG.debug("Given time \"{}\" is correct.", time);
            return true;
        } catch (DateTimeParseException e) {
            LOG.warn("DateTimeParseException in isTimeValid method. Given time is not correct");
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
        return city.matches(CITY_REGEX);
    }

    public boolean isInputValid(String input) {
        return input.matches(INPUT_REGEX);
    }

    public boolean isInputTextNumberValid(String input) {
        return input.matches(INPUT_TEXT_NUMBER_REGEX);
    }

    public boolean isStreetValid(String street) {
        return street.matches(STREET_REGEX);
    }

    public boolean isStreetNameValid(String streetName) {
        return streetName.matches(STREET_NAME_REGEX);
    }

    public boolean isStreetNumberValid(String streetNumber) {
        return streetNumber.matches(STREET_NUMBER_REGEX);
    }

    public boolean isNumberValid(String number){
       return number.matches(NUMBER_REGEX);
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
