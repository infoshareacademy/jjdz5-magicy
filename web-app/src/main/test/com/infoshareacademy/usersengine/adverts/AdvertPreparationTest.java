package com.infoshareacademy.usersengine.adverts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AdvertPreparation Test")
class AdvertPreparationTest {

    private static final LocalDate CORRECT_DATE = LocalDate.now().plusDays(1);

    private static final String START_CITY_KEY = "startCity";
    private static final String START_STREET_KEY = "startStreet";
    private static final String START_TIME_KEY = "startTime";
    private static final String END_CITY_KEY = "endCity";
    private static final String END_STREET_KEY = "endStreet";
    private static final String END_TIME_KEY = "endTime";
    private static final String PICK_UP_CITY_KEY = "pickUpCity";
    private static final String PICK_UP_STREET_KEY = "pickUpStreet";
    private static final String PICK_UP_TIME_KEY = "pickUpTime";
    private static final String DATE_KEY = "date";

    private static final String START_CITY_VALUE = "Start City";
    private static final String START_STREET_VALUE = "Start Street";
    private static final String START_TIME_VALUE = "08:00";
    private static final String END_CITY_VALUE = "End City";
    private static final String END_STREET_VALUE = "End Street";
    private static final String END_TIME_VALUE = "10:00";
    private static final String PICK_UP_CITY_VALUE = "Pick Up City";
    private static final String PICK_UP_STREET_VALUE = "Pick Up Street";
    private static final String PICK_UP_TIME_VALUE = "09:00";
    private static final String DATE_VALUE = CORRECT_DATE.toString();

    private AdvertPreparation advertPreparation;
    private AdvertData advertDataFromSetters;
    private Map<String, String[]> parametersMap;

    @BeforeEach
    void setUp() {
        advertPreparation = new AdvertPreparation();
        advertDataFromSetters = new AdvertData();
        advertDataFromSetters.setStartCity(START_CITY_VALUE);
        advertDataFromSetters.setStartStreet(START_STREET_VALUE);
        advertDataFromSetters.setStartTime(START_TIME_VALUE);
        advertDataFromSetters.setEndCity(END_CITY_VALUE);
        advertDataFromSetters.setEndStreet(END_STREET_VALUE);
        advertDataFromSetters.setEndTime(END_TIME_VALUE);
        advertDataFromSetters.setPickUpCity(PICK_UP_CITY_VALUE);
        advertDataFromSetters.setPickUpStreet(PICK_UP_STREET_VALUE);
        advertDataFromSetters.setPickUpTime(PICK_UP_TIME_VALUE);
        advertDataFromSetters.setDate(DATE_VALUE);
    }

    @Test
    @DisplayName("Should return AdvertData object, based on given Map<String, String[]> with parameters.")
    void returnsAdvertDataObjectBasedOnGivenMapInMapReaderMethod() {

        // arrange
        prepareParametersMap();

        // act
        AdvertData advertDataFromAdvertPreparation =  advertPreparation.mapReader(parametersMap);

        // assert
        assertThat(advertDataFromAdvertPreparation).isEqualToComparingFieldByField(advertDataFromSetters);
    }

    @Test
    @DisplayName("Should return empty message if all of main values are correct.")
    void returnsEmptyMessageWhenMainValuesAreCorrectInValidateAdvertDataMethod() {

        // act
        String message = advertPreparation.validateAdvertData(advertDataFromSetters);

        // assert
        assertThat(message).isEmpty();
    }

    @Test
    @DisplayName("Should return message if any of main values are incorrect.")
    void returnsAppropriateMessageWhenAnyOfMainValuesAreIncorrectInValidateAdvertDataMethod() {

        // arrange
        AdvertData advertData = new AdvertData();
        advertData.setPickUpCity("");
        advertData.setPickUpStreet("");
        advertData.setPickUpTime("");

        // act
        String message = advertPreparation.validateAdvertData(advertData);

        // assert
        assertThat(message).isNotEmpty().isNotEqualToIgnoringCase("");
    }

    private void prepareParametersMap() {
        parametersMap = new HashMap<>();
        parametersMap.put(START_CITY_KEY, new String[] {START_CITY_VALUE});
        parametersMap.put(START_STREET_KEY, new String[] {START_STREET_VALUE});
        parametersMap.put(START_TIME_KEY, new String[] {START_TIME_VALUE});
        parametersMap.put(END_CITY_KEY, new String[] {END_CITY_VALUE});
        parametersMap.put(END_STREET_KEY, new String[] {END_STREET_VALUE});
        parametersMap.put(END_TIME_KEY, new String[] {END_TIME_VALUE});
        parametersMap.put(PICK_UP_CITY_KEY, new String[] {PICK_UP_CITY_VALUE});
        parametersMap.put(PICK_UP_STREET_KEY, new String[] {PICK_UP_STREET_VALUE});
        parametersMap.put(PICK_UP_TIME_KEY, new String[] {PICK_UP_TIME_VALUE});
        parametersMap.put(DATE_KEY, new String[] {DATE_VALUE});
    }

}