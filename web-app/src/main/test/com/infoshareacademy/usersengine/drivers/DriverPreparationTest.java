package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Driver preparation test")
class DriverPreparationTest {
    private static final String MESSAGE_DRIVER_ALREADY_EXIST = "Driver already exist <br>";
    private static final String MESSAGE_INCORRECT_NAME = "Enter correct name </br>";
    private static final String MESSAGE_INCORRECT_SURNAME = "Enter correct surname </br>";
    private static final String MESSAGE_INCORRECT_PHONE_NUMBER = "Enter correct phone number </br>";
    private static final String MESSAGE_INCORRECT_CITY = "Enter correct city <br>";
    private static final String MESSAGE_INCORRECT_DISTRICT = "Enter correct district <br>";

    private static final String NAME_KEY = "name";
    private static final String SURNAME_KEY = "surname";
    private static final String PHONE_KEY = "phone";
    private static final String CITY_KEY = "city";
    private static final String DISTRICT_KEY = "district";

    private static final String NAME_VALUE = "Marysia";
    private static final String SURNAME_VALUE = "Wee";
    private static final String PHONE_VALUE = "666 666 666";
    private static final String CITY_VALUE = "Gdynia";
    private static final String DISTRICT_VALUE = "Działki Leśne";
    private Driver driver = new Driver();
    private DriverPreparation testee;
    private Map<String, String[]> map;

    @BeforeEach
    void setUp(){
        testee = new DriverPreparation();
    }

    @Test
    @DisplayName("Should return driver when given map is correct")
    void runWithCorrectParametersMapThenGiveDriver(){
        //GIVEN
        Map<String, String[]> map = createDriveParametersMap();
        Driver expected = createDriver(driver);
        //WHEN
        Driver result = testee.mapReader(map);
        //THEN
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Should return empty string message when given params are correct")
    void runWithCorrectParamsThenGiveEmptyMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertThat(message).isEmpty();
    }

    @Test
    @DisplayName("Should return message that driver already exist when given driver is duplicated")
    void runWithDuplicateDriverDataThenGiveProperMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        drivers.add(newDriver);
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertEquals(MESSAGE_DRIVER_ALREADY_EXIST, message);
    }

    @Test
    @DisplayName("Should return message that name is incorrect when given name doesn't have correct value")
    void runWithWrongNameValueThenGiviveProperMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        newDriver.setName("1234");
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertEquals(MESSAGE_INCORRECT_NAME, message);
    }

    @Test
    @DisplayName("Should return message that surname is incorrect when given surname doesn't have correct value")
    void runWithWrongSurnameValueThenGiviveProperMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        newDriver.setSurname("???");
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertEquals(MESSAGE_INCORRECT_SURNAME, message);
    }

    @Test
    @DisplayName("Should return message that phone number is incorrect when given phone doesn't have correct value")
    void runWithWrongPhoneValueThenGiviveProperMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        newDriver.setPhone("numer telefonu");
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertEquals(MESSAGE_INCORRECT_PHONE_NUMBER, message);
    }

    @Test
    @DisplayName("Should return message that city is incorrect when given city doesn't have correct value")
    void runWithWrongCityValueThenGiviveProperMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        newDriver.setCity("///");
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertEquals(MESSAGE_INCORRECT_CITY, message);
    }

    @Test
    @DisplayName("Should return message that district is incorrect when given district doesn't have correct value")
    void runWithWrongDistrictValueThenGiviveProperMessage(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        newDriver.setDistrict("'...[}OU*^^");
        //WHEN
        String message = testee.validateDriver(driver, drivers);
        //THEN
        assertEquals(MESSAGE_INCORRECT_DISTRICT, message);
    }

    @Test
    @DisplayName("Should return driver with correct id param")
    void runWithNewDriverDataThenSetCorrectDriverId(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = createDriver(driver);
        Driver expected = createDriver(driver);
        expected.setId(1L);
        //WHEN
        Driver result = testee.getNewDriver(drivers, newDriver);
        //THEn
        assertEquals(expected, result);
    }

    Map<String, String[]> createDriveParametersMap(){
        Map<String, String[]> map = new HashMap<>();
        map.put(NAME_KEY, new String[] {NAME_VALUE});
        map.put(SURNAME_KEY, new String[] {SURNAME_VALUE});
        map.put(PHONE_KEY, new String[] {PHONE_VALUE});
        map.put(CITY_KEY, new String[] {CITY_VALUE});
        map.put(DISTRICT_KEY, new String[] {DISTRICT_VALUE});

        return map;
    }

    Driver createDriver(Driver driver){
        driver.setName(NAME_VALUE);
        driver.setSurname(SURNAME_VALUE);
        driver.setPhone(PHONE_VALUE);
        driver.setCity(CITY_VALUE);
        driver.setDistrict(DISTRICT_VALUE);
        driver.setRating(new Rating());

        return driver;
    }


}
