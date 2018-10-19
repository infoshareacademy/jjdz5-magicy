package com.infoshareacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@DisplayName("UserInput test")
class UserInputTest {
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private UserInput userInput;

    @BeforeEach
    void setUp(){
        userInput = new UserInput();
    }

    @Test
    @DisplayName("Should return valid date format from String given by user")
    void returnValidDateFormatFromStringGivenByUser() throws ParseException {
        String inputFromUser = "11-09-1990";
        Date dateFromUser = new SimpleDateFormat(DATE_FORMAT).parse(inputFromUser);

        InputStream in = new ByteArrayInputStream(inputFromUser.getBytes());
        System.setIn(in);

        Date result = userInput.askForDate("input date");

        assertEquals(result, dateFromUser);
    }

    @Test
    @DisplayName("Should return valid time from String given by user")
    void returnValidTimeFromStringGivenByUser(){
        String timeFromUser = "12:58";

        InputStream in = new ByteArrayInputStream(timeFromUser.getBytes());
        System.setIn(in);

        String result = userInput.askForTime("input time");

        assertEquals(result, timeFromUser);
    }

    @Test
    @DisplayName("Should return valid name of city from String given by user")
    void returnValidCityFromStringGivenByUser(){
        String cityFromUser = "Gdynia";

        InputStream in = new ByteArrayInputStream(cityFromUser.getBytes());
        System.setIn(in);

        String result = userInput.askForCity("input city");

        assertEquals(result, cityFromUser);
    }

    @Test
    @DisplayName("Should return valid name of Street from String given by user")
    void returnValidStreetFromStringGivenByUser(){
        String streetFromUser = "Legionów 56";

        InputStream in = new ByteArrayInputStream(streetFromUser.getBytes());
        System.setIn(in);

        String result = userInput.askForStreet("Input street");

        assertEquals(result, streetFromUser);
    }

    @Test
    @DisplayName("Should return false if given time is empty")
    void returnFalseIfGivenTimeIsEmpty(){
        String timeFromUser = "";

        Boolean result = userInput.isTimeValid(timeFromUser);

        assertFalse("time is empty", result);
    }

    @Test
    @DisplayName("Should return true if given time is valid")
    void returnTrueIfGivenTimeIsValid(){
        String timeFromUser = "12:58";

        Boolean result = userInput.isTimeValid(timeFromUser);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given time is invalid")
    void returnFalseIfGivenTimeIsInValid(){
        String timeFromUser = "11-34";

        Boolean result = userInput.isTimeValid(timeFromUser);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given date is valid")
    void returnTrueIfGivenDateIsValid(){
        String dateFromUser = "11-09-1990";

        boolean result = userInput.isDateValid(dateFromUser);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given date is invalid")
    void returnFalseIfGivenDateIsInValid(){
        String dateFromUser = "11.09.1990";

        boolean result = userInput.isDateValid(dateFromUser);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given city is valid")
    void returnTrueIfGivenCityIsValid(){
        String cityFromUser = "Gdynia";

        boolean result = userInput.isCityValid(cityFromUser);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given city is invalid")
    void returnFalseIfGivenCityIsInValid(){
        String cityFromUser = "Gd nia8";

        boolean result = userInput.isCityValid(cityFromUser);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given input is valid")
    void returnTrueIfGivenInputIsValid(){
        String inputFromUser = "Krzysztof";

        boolean result = userInput.isInputValid(inputFromUser);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given input is invalid")
    void returnFalseIfGivenInputIsInValid(){
        String inputFromUser = "K86ls";

        boolean result = userInput.isInputValid(inputFromUser);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given street is valid")
    void returnTrueIfGivenStreetIsValid(){
        String streetFromUser = "Legionów 56";

        boolean result = userInput.isStreetValid(streetFromUser);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given street is invalid")
    void returnFalseIfGivenStreetIsInValid(){
        String streetFromUser = "56 Legionów";

        boolean result = userInput.isStreetValid(streetFromUser);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given number is valid")
    void returnTrueIfGivenNumberIsValid(){
        String numberFromUser = "555 555 555";

        boolean result = userInput.isNumberValid(numberFromUser);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given number is invalid")
    void returnFalseIfGivenNumberIsInValid(){
        String numberFromUser = "555555555";

        boolean result = userInput.isNumberValid(numberFromUser);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return valid rating number from String given by user")
    void returnValidRatingFromStringGivenByUser(){
        String ratingFromUser = "4";

        InputStream in = new ByteArrayInputStream(ratingFromUser.getBytes());
        System.setIn(in);

        int result = userInput.askForRating("input rating");

        assertEquals(4, result);
    }

    @Test
    @DisplayName("Should return driver's id from String given by user")
    void returnDriverIdFromStringGivenByUser(){
        String idFromUser = "3";

        InputStream in = new ByteArrayInputStream(idFromUser.getBytes());
        System.setIn(in);

        int result = userInput.askForRating("input user id");

        assertEquals(3, result);
    }























}


