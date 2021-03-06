package com.infoshareacademy.usersengine.drivers;

import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Driver validation test")
class DriversValidationBeanTest {
    private static final String RATING = "4";

    private static final String MESSAGE_SOMETHING_WENT_WRONG = "Ups! Something went wrong :( Try again";
    private static final String MESSAGE_ADD_RATING_USING_ALERT = "Ups! Something went wrong. Add rating using alert.";
    private DriversValidationBean testee;

    @BeforeEach
    void setUp(){
        testee = new DriversValidationBean();
    }

    @Test
    @DisplayName("Should give true when given rating has correct value")
    void runWithCorrectRatingValueThenReturnTrue(){
        //GIVEN
        String rating = RATING;
        //WHEN
        Boolean isCorrect = testee.checkIsDriverRatingValid(rating);
        //THEN
        assertThat(isCorrect).isTrue();
    }

    @Test
    @DisplayName("Should give false when given rating has incorrect value")
    void runWithIncorrectRatingValueThenReturnFalse(){
        //GIVEN
        String rating = "100";
        //WHEN
        Boolean isCorrect = testee.checkIsDriverRatingValid(rating);
        //THEN
        assertThat(isCorrect).isFalse();
    }

    @Test
    @DisplayName("Should give false when given rating is empty")
    void runWithEmptyRatingValueThenReturnFalse(){
        //GIVEN
        String rating = "";
        //WHEN
        Boolean isCorrect = testee.checkIsDriverRatingValid(rating);
        //THEN
        assertThat(isCorrect).isFalse();
    }

    @Test
    @DisplayName("Should give false when given phone number is not on the list")
    void runWithNewPhoneNumberThenReturnFalse(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = new Driver("Marysia", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 1L);
        drivers.add(newDriver);
        String phone = "111 222 333";
        //WHEN
        Boolean isPhoneOnTheList = testee.isPhoneNumberExist(phone, drivers);
        //THEN
        assertThat(isPhoneOnTheList).isFalse();
    }

    @Test
    @DisplayName("Should return true when given phone number is on the list")
    void runWithPhoneNumberWhichIsOnTheListThenReturnTrue(){
        //GIVEN
        List<Driver> drivers = new ArrayList<>();
        Driver newDriver = new Driver("Marysia", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 1L);
        drivers.add(newDriver);
        String phone = "555 555 555";
        //WHEN
        Boolean isPhoneOnTheList = testee.isPhoneNumberExist(phone, drivers);
        //THEN
        assertThat(isPhoneOnTheList).isTrue();
    }

    @Test
    @DisplayName("Should return false when given number is null")
    void runWithNullNumberParamThenReturnFalse(){
        //GIVEN
        String number = null;
        //WHEN
        Boolean isNumber = testee.askForNumber(number);
        //THEN
        assertThat(isNumber).isFalse();
    }

    @Test
    @DisplayName("Should return false when given number is empty")
    void runWithEmptyNumberParamThenReturnFalse(){
        //GIVEN
        String number = "";
        //WHEN
        Boolean isNumber = testee.askForNumber(number);
        //THEN
        assertThat(isNumber).isFalse();

    }

    @Test
    @DisplayName("Should return false when given input is not a number")
    void runWithIncorrectNumberParamThenReturnFalse(){
        //GIVEN
        String number = "m";
        //WHEN
        Boolean isNumber = testee.askForNumber(number);
        //THEN
        assertThat(isNumber).isFalse();

    }

    @Test
    @DisplayName("Should return true when given input is a number")
    void runWithCorrectNumberParamThenReturnTrue(){
        //GIVEN
        String number = "111 223 333";
        //WHEN
        Boolean isNumber = testee.askForNumber(number);
        //THEN
        assertThat(isNumber).isTrue();
    }

    @Test
    @DisplayName("Should return false when given text is null")
    void runWithNullTextParamThenReturnFalse(){
        //GIVEN
        String text = null;
        //WHEN
        Boolean isText = testee.askForText(text);
        //THEN
        assertThat(isText).isFalse();
    }

    @Test
    @DisplayName("Should return false when given text is empty")
    void runWithEmptyTextParamThenReturnFalse(){
        //GIVEN
        String text = "";
        //WHEN
        Boolean isText = testee.askForText(text);
        //THEN
        assertThat(isText).isFalse();
    }

    @Test
    @DisplayName("Should return false when given text is incorrect")
    void runWithIncorrectTextParamThenReturnFalse(){
        //GIVEN
        String text = "*&(&";
        //WHEN
        Boolean isText = testee.askForText(text);
        //THEN
        assertThat(isText).isFalse();
    }

    @Test
    @DisplayName("Should return true when given text is correct")
    void runWithCorrectTextParamThenReturnTrue(){
        //GIVEN
        String text = "tekst";
        //WHEN
        Boolean isText = testee.askForText(text);
        //THEN
        assertThat(isText).isTrue();
    }

    @Test
    @DisplayName("Should return false when given driver id param is not used")
    void runWithNewDriverIdThenReturnFalse(){
        //GIVEN
        String newId = "3";
        List<Driver> drivers = createDriversList();
        //WHEN
        Boolean idAlreadyUsed = testee.checkIsDriverIdAlreadyUsed(drivers, newId);
        //THEN
        assertThat(idAlreadyUsed).isFalse();
    }

    @Test
    @DisplayName("Should return true when given driver's id param already exist")
    void runWithExistingDriverIdThenReturnTrue(){
        //GIVEN
        String newId = "2";
        List<Driver> drivers = createDriversList();
        //WHEN
        Boolean idAlreadyUsed = testee.checkIsDriverIdAlreadyUsed(drivers, newId);
        //THEN
        assertThat(idAlreadyUsed).isTrue();
    }

    @Test
    @DisplayName("Should return false when given driver's id is not used")
    void checkIfNewDriverIdIsUsedIfNotReturnFalse(){
        //GIVEN
        String newId = "3";
        List<Driver> drivers = createDriversList();
        //WHEN
        Boolean idAlreadyUsed = testee.askForDriverId(drivers, newId);
        //THEN
        assertThat(idAlreadyUsed).isFalse();
    }

    @Test
    @DisplayName("Should return true when given driver's id is used")
    void returnTrueIfNewDriverIdIsUsed(){
        //GIVEN
        String newId = "2";
        List<Driver> drivers = createDriversList();
        //WHEN
        Boolean idAlreadyUsed = testee.askForDriverId(drivers, newId);
        //THEN
        assertThat(idAlreadyUsed).isTrue();
    }

    @Test
    @DisplayName("Should return true when given rating value is correct")
    void runWithCorrectRatingParamThenReturnTrue(){
        //GIVEN
        String rating = "3";
        //WHEN
        Boolean isRating = testee.askForRating(rating);
        //THEN
        assertThat(isRating).isTrue();
    }

    @Test
    @DisplayName("Should return false when given rating value is incorrect")
    void runWithIncorrectRatingParamThenReturnFalse(){
        //GIVEN
        String rating = "30000";
        //WHEN
        Boolean isRating = testee.askForRating(rating);
        //THEN
        assertThat(isRating).isFalse();
    }

    @Test
    @DisplayName("Should return false when given rating value is null")
    void runWithNullRatingParamThenReturnFalse(){
        //GIVEN
        String rating = null;
        //WHEN
        Boolean isRating = testee.askForRating(rating);
        //THEN
        assertThat(isRating).isFalse();
    }

    @Test
    @DisplayName("Should return false when given rating value is empty")
    void runWitEmptylRatingParamThenReturnFalse(){
        //GIVEN
        String rating = "";
        //WHEN
        Boolean isRating = testee.askForRating(rating);
        //THEN
        assertThat(isRating).isFalse();
    }

    @Test
    @DisplayName("Should return empty message when given rating is correct")
    void runWithCorrecRatingDataThenReturnEmptyMessage(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        String id = "3";
        String rating = "5";
        //WHEN
        String message = testee.validateDriverData(id,rating,drivers);
        //THEN
        assertThat(message).isEmpty();
    }
    @Test
    @DisplayName("Should return something went wrong message when given rating is incorrect")
    void runWithIncorrecRatingDataThenReturnNiceMessage(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        String id = "2";
        String rating = "5";
        //WHEN
        String message = testee.validateDriverData(id,rating,drivers);
        //THEN
        assertEquals(MESSAGE_SOMETHING_WENT_WRONG, message);
    }

    @Test
    @DisplayName("Should return two messages when given is wrong id and rating params")
    void runWithIncorrectRatingDataThenReturnUpsMessage(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        String id = "2";
        String rating = "100";
        //WHEN
        String message = testee.validateDriverData(id,rating,drivers);
        //THEN
        assertEquals(MESSAGE_SOMETHING_WENT_WRONG + MESSAGE_ADD_RATING_USING_ALERT, message);
    }

    @Test
    @DisplayName("Should return message add rating using alert when given is wrong rating param")
    void runWithIncorrectRatingParamThenReturnUpsMessage(){
        //GIVEN
        List<Driver> drivers = createDriversList();
        String id = "3";
        String rating = "100";
        //WHEN
        String message = testee.validateDriverData(id,rating,drivers);
        //THEN
        assertEquals(MESSAGE_ADD_RATING_USING_ALERT, message);
    }

    List<Driver> createDriversList(){
        List<Driver> drivers = new ArrayList<>();
        Driver driver1 = new Driver("Marysia", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 1L);
        Driver driver2 = new Driver("Janek", "Wee", "555 555 555", "Gdynia", "Witomino", new Rating(), 2L);
        drivers.add(driver1);
        drivers.add(driver2);
        return drivers;
    }

}
