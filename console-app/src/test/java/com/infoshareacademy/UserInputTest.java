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
        //arrange
        String inputFromUser = "11-09-1990";
        Date dateFromUser = new SimpleDateFormat(DATE_FORMAT).parse(inputFromUser);
        InputStream in = new ByteArrayInputStream(inputFromUser.getBytes());
        System.setIn(in);
        //act
        Date result = userInput.askForDate("input date");
        //assert
        assertEquals(result, dateFromUser);
    }

    @Test
    @DisplayName("Should return valid time from String given by user")
    void returnValidTimeFromStringGivenByUser(){
        //arrange
        String timeFromUser = "12:58";
        InputStream in = new ByteArrayInputStream(timeFromUser.getBytes());
        System.setIn(in);
        //act
        String result = userInput.askForTime("input time");
        //assert
        assertEquals(result, timeFromUser);
    }

    @Test
    @DisplayName("Should return valid name of city from String given by user")
    void returnValidCityFromStringGivenByUser(){
        //arrange
        String cityFromUser = "Gdynia";
        InputStream in = new ByteArrayInputStream(cityFromUser.getBytes());
        System.setIn(in);
        //act
        String result = userInput.askForCity("input city");
        //assert
        assertEquals(result, cityFromUser);
    }

    @Test
    @DisplayName("Should return valid name of Street from String given by user")
    void returnValidStreetFromStringGivenByUser(){
        //arrange
        String streetFromUser = "Legionów 56";
        InputStream in = new ByteArrayInputStream(streetFromUser.getBytes());
        System.setIn(in);
        //act
        String result = userInput.askForStreet("Input street");
        //assert
        assertEquals(result, streetFromUser);
    }

    @Test
    @DisplayName("Should return false if given time is empty")
    void returnFalseIfGivenTimeIsEmpty(){
        //arrange
        String timeFromUser = "";
        //act
        Boolean result = userInput.isTimeValid(timeFromUser);
        //assert
        assertFalse("time is empty", result);
    }

    @Test
    @DisplayName("Should return true if given time is valid")
    void returnTrueIfGivenTimeIsValid(){
        //arrange
        String timeFromUser = "12:58";
        //act
        Boolean result = userInput.isTimeValid(timeFromUser);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given time is invalid")
    void returnFalseIfGivenTimeIsInValid(){
        //arrange
        String timeFromUser = "11-34";
        //act
        Boolean result = userInput.isTimeValid(timeFromUser);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given date is valid")
    void returnTrueIfGivenDateIsValid(){
        //arrange
        String dateFromUser = "11-09-1990";
        //act
        boolean result = userInput.isDateValid(dateFromUser);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given date is invalid")
    void returnFalseIfGivenDateIsInValid(){
        //arrange
        String dateFromUser = "11.09.1990";
        //act
        boolean result = userInput.isDateValid(dateFromUser);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given city is valid")
    void returnTrueIfGivenCityIsValid(){
        //arrange
        String cityFromUser = "Gdynia";
        //act
        boolean result = userInput.isCityValid(cityFromUser);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given city is invalid")
    void returnFalseIfGivenCityIsInValid(){
        //arrange
        String cityFromUser = "Gd nia8";
        //act
        boolean result = userInput.isCityValid(cityFromUser);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given input is valid")
    void returnTrueIfGivenInputIsValid(){
        //arrange
        String inputFromUser = "Krzysztof";
        //act
        boolean result = userInput.isInputValid(inputFromUser);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given input is invalid")
    void returnFalseIfGivenInputIsInValid(){
        //arrange
        String inputFromUser = "K86ls";
        //act
        boolean result = userInput.isInputValid(inputFromUser);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given street is valid")
    void returnTrueIfGivenStreetIsValid(){
        //arrange
        String streetFromUser = "Legionów 56";
        //act
        boolean result = userInput.isStreetValid(streetFromUser);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given street is invalid")
    void returnFalseIfGivenStreetIsInValid(){
        //arrange
        String streetFromUser = "56 Legionów";
        //act
        boolean result = userInput.isStreetValid(streetFromUser);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if given number is valid")
    void returnTrueIfGivenNumberIsValid(){
        //arrange
        String numberFromUser = "555 555 555";
        //act
        boolean result = userInput.isNumberValid(numberFromUser);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if given number is invalid")
    void returnFalseIfGivenNumberIsInValid(){
        //arrange
        String numberFromUser = "555555555";
        //act
        boolean result = userInput.isNumberValid(numberFromUser);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return valid rating number from String given by user")
    void returnValidRatingFromStringGivenByUser(){
        //arrange
        String ratingFromUser = "4";
        InputStream in = new ByteArrayInputStream(ratingFromUser.getBytes());
        System.setIn(in);
        //act
        int result = userInput.askForRating("input rating");
        //assert
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Should return driver's id from String given by user")
    void returnDriverIdFromStringGivenByUser(){
        //arrange
        String idFromUser = "3";
        InputStream in = new ByteArrayInputStream(idFromUser.getBytes());
        System.setIn(in);
        //act
        int result = userInput.askForRating("input user id");
        //assert
        assertEquals(3, result);
    }























}


