package com.infoshareacademy.usersengine.adverts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("AdvertsValidation Test")
class AdvertsValidationBeanTest {

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate INCORRECT_DATE_PAST = LocalDate.now().minusDays(1);
    private static final LocalDate INCORRECT_DATE_TOO_LATE = LocalDate.now().plusMonths(1);
    private static final String EMPTY_INPUT = "";
    private static final String CORRECT_STREET = "Grunwaldzka 472";
    private static final String INCORRECT_STREET = "472 Grunwaldzka";
    private static final String CORRECT_CITY = "Gdańsk";

    private AdvertsValidation advertsValidation;

    @BeforeEach
    void setUp() {
        advertsValidation = new AdvertsValidationBean();
    }

    @Test
    @DisplayName("Should return true when date is correct.")
    void returnsTrueWhenDateIsCorrectCheckDateMethod() {

        // arrange
        Integer currentMonthRange = Math.toIntExact(TODAY.range(ChronoField.DAY_OF_MONTH).getMaximum());
        LocalDate correctDate = TODAY.plusDays(new Random()
                .ints(1, 1, (currentMonthRange - 1)).sum());

        // assert
        assertThat(advertsValidation.checkDate(correctDate.toString())).isTrue();
    }

    @Test
    @DisplayName("Should return false when input date is same as today's date.")
    void returnsFalseWhenInputDateIsSameAsTodaysDateInCheckDateMethod() {
        assertThat(advertsValidation.checkDate(TODAY.toString())).isFalse();
    }

    @Test
    @DisplayName("Should return false when input date is past.")
    void returnsFalseWhenInputDateIsPastInCheckDateMethod() {
        assertThat(advertsValidation.checkDate(INCORRECT_DATE_PAST.toString())).isFalse();
    }

    @Test
    @DisplayName("Should return false when input date is too late.")
    void returnsFalseWhenInputDateIsTooLateInCheckDateMethod() {
        assertThat(advertsValidation.checkDate(INCORRECT_DATE_TOO_LATE.toString())).isFalse();
    }

    @Test
    @DisplayName("Should return false when date is empty.")
    void returnsFalseWhenDateIsEmptyInAskForDateMethod() {
        assertThat(advertsValidation.askForDate(EMPTY_INPUT)).isFalse();
    }

    @Test
    @DisplayName("Should return false when date is a null.")
    void returnsFalseWhenDateIsNullInAskForDateMethod() {
        assertThat(advertsValidation.askForDate(null)).isFalse();
    }

    @Test
    @DisplayName("Should return true when street is correct.")
    void returnsTrueWhenStreetIsCorrectInAskForStreetMethod() {
        assertThat(advertsValidation.askForStreet(CORRECT_STREET)).isTrue();
    }

    @Test
    @DisplayName("Should return false when street is incorrect.")
    void returnsFalseWhenStreetIsIncorrectInAskForStreetMethod() {
        assertThat(advertsValidation.askForStreet(INCORRECT_STREET)).isFalse();
    }

    @Test
    @DisplayName("Should return false when street is empty.")
    void returnsFalseWhenStreetIsEmptyInAskForStreetMethod() {
        assertThat(advertsValidation.askForStreet(EMPTY_INPUT)).isFalse();
    }

    @Test
    @DisplayName("Should return false when street is a null.")
    void returnsFalseWhenStreetIsNullInAskForStreetMethod() {
        assertThat(advertsValidation.askForStreet(null)).isFalse();
    }

    @Test
    @DisplayName("Should return true when city is correct.")
    void returnsTrueWhenCityIsCorrectInAskForCityMethod() {
        assertThat(advertsValidation.askForCity(CORRECT_CITY)).isTrue();
    }

    @Test
    @DisplayName("Should return false when city is incorrect.")
    void returnsFalseWhenCityIsIncorrectInAskForCityMethod() {
        String incorrectCity = String.valueOf(new Random().nextInt());
        assertThat(advertsValidation.askForCity(incorrectCity)).isFalse();
    }

    @Test
    @DisplayName("Should return false when city is empty.")
    void returnsFalseWhenCityIsEmptyInAskForCityMethod() {
        assertThat(advertsValidation.askForCity(EMPTY_INPUT)).isFalse();
    }

    @Test
    @DisplayName("Should return false when city is a null.")
    void returnsFalseWhenCityIsNullInAskForCityMethod() {
        assertThat(advertsValidation.askForCity(null)).isFalse();
    }

    @Test
    @DisplayName("Should return true when time is correct.")
    void returnsTrueWhenTimeIsCorrectInAskForTimeMethod() {

        // arrange
        String hour = String.valueOf(new Random().ints(1, 0, 23).sum());
        String minute = String.valueOf(new Random().ints(1, 0, 59).sum());
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String correctTimeToParse = hour + ":" + minute;

        //assert
        assertThat(advertsValidation.askForTime(correctTimeToParse)).isTrue();
    }

    @Test
    @DisplayName("Should return true when time is correct.")
    void returnsFalseWhenTimeIsIncorrectInAskForTimeMethod() {

        // arrange
        String incorrectTime = String.valueOf(new Random().nextInt());

        // assert
        assertThat(advertsValidation.askForTime(incorrectTime)).isFalse();
    }

    @Test
    @DisplayName("Should return false when time is empty.")
    void returnsFalseWhenTimeIsEmptyInAskForTimeMethod() {
        assertThat(advertsValidation.askForTime(EMPTY_INPUT)).isFalse();
    }

    @Test
    @DisplayName("Should return false when time is a null.")
    void returnsFalseWhenTimeIsNullInAskForTimeMethod() {
        assertThat(advertsValidation.askForTime(null)).isFalse();
    }

}