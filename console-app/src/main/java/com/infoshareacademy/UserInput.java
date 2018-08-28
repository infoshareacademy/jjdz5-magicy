package com.infoshareacademy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class UserInput {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    Date askForDate(final String question) {
        String userDate = getUserInput(question).trim();
        while (!isDateValid(userDate)) {
            System.out.println("Entered date is incorrect, please try again.");
            userDate = getUserInput(userDate);
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
            userTime = getUserInput(userTime);
        }

        return userTime;
    }

    String askForText(final String question) {
        return this.getUserInput(question);
    }

     String getUserInput(final String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + "> ");

        return scanner.nextLine();
    }

    private boolean isTimeValid(String time) {
        if (time.isEmpty()) {
            return false;
        }

        String[] splittedTime = time.split(":");

        if (splittedTime.length != 2) {
            return false;
        }

        final Integer hours = Integer.parseInt(splittedTime[0]);
        final Integer minutes = Integer.parseInt(splittedTime[1]);

        if (hours < 0 || hours > 24 || minutes < 0 || minutes > 59) {
            return false;
        }

        return true;
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
