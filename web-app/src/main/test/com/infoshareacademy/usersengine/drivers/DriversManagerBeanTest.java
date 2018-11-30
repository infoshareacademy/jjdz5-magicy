package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Driver manager test")
class DriversManagerBeanTest {
    private DriversManagerBean testee;

    @BeforeEach
    void setUp(){
        testee = new DriversManagerBean();
    }

    @Test
    @DisplayName("Should return optional of driver when given id is used")
    void runWithExistingIdParamThenReturnDriverById(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        Long id = 1L;
        Optional<Driver> expected = Optional.of(new Driver("Marysia", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 1L));
        //THEN
        Optional<Driver> result = testee.getDriverById(drivers, id);
        //WHEN
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return empty optional when given id is not used")
    void runWithIncorrectIdParamThenReturnEmptyOptional(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        Long id = 100L;
        Optional<Driver> expected = Optional.empty();
        //THEN
        Optional<Driver> result = testee.getDriverById(drivers, id);
        //WHEN
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return updated drivers list when given parameters are correct")
    void runWithCorrectDriverDataThenUpdateRating(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        List<Driver> expected = updatedDriversList();
        Long id = 1L;
        Integer rating = 5;

        //THEN
        List<Driver> result = testee.updateDriversList(drivers, rating, id);
        //WHEN
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return not updated drivers list when given id is not used")
    void runWithIncorrectIdThenDoNotUpdated(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        Long id = 0L;
        Integer rating = 5;
        //THEN
        List<Driver> result = testee.updateDriversList(drivers, rating, id);
        //WHEN
        assertEquals(drivers, result);
    }

    @Test
    @DisplayName("Should return not updated drivers list when given rating is not correct")
    void runWithIncorrectRatingThenDoNotUpdated(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        Long id = 1L;
        Integer rating = 50;
        //THEN
        List<Driver> result = testee.updateDriversList(drivers, rating, id);
        //WHEN
        assertEquals(drivers, result);
    }

    @Test
    @DisplayName("Should return next driver id when given is list of drivers")
    void runWithDriversListThenReturnNextDriverId(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        Long expected = 3L;
        //THEN
        Long result = testee.getNextDriverId(drivers);
        //WHEN
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return id 1L when given is empty list of drivers")
    void runWithEmptyDriversListThenReturnNextDriverId(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Long expected = 1L;
        //THEN
        Long result = testee.getNextDriverId(drivers);
        //WHEN
        assertEquals(expected, result);
    }


    List<Driver> createDriversList(){
        List<Driver> drivers = new ArrayList<>();
        Driver driver1 = new Driver("Marysia", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 1L);
        Driver driver2 = new Driver("Janek", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 2L);
        drivers.add(driver1);
        drivers.add(driver2);
        return drivers;
    }
    List<Driver> updatedDriversList(){
        List<Driver> drivers = new ArrayList<>();
        Rating rating = new Rating(5.00, 1,1L);
        Driver driver1 = new Driver("Marysia", "Wee", "555 555 555", "Gdynia", "Witomino", rating, 1L);
        Driver driver2 = new Driver("Janek", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 2L);
        drivers.add(driver1);
        drivers.add(driver2);
        return drivers;
    }
}
