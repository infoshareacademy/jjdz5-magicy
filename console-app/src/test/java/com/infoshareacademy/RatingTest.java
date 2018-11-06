package com.infoshareacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RatingTest {

    Rating rating;

    @BeforeEach
    void setUp(){
        rating = new Rating();
        rating.setAverage(4.3);
        rating.setPersons(5);
    }

    @Test
    @DisplayName("Should return new correct average from note given by user")
    void newAverageGivenCorrectValue() {
        //arrange
        Integer noteFromUser = 3;
        Double newValidAverage = 4.1;
        //act
        Double average = rating.computeNewAverage(noteFromUser);
        //assert
        assertEquals(newValidAverage, average);
    }

    @Test
    @DisplayName("throws null pointer exception when param is null")
    void  newAverageGivenNullParamThenException(){
        //arrange
        Integer noteFromUser = null;
        //act
        //assert
        assertThrows(NullPointerException.class, () ->
                rating.computeNewAverage(noteFromUser) );
    }

    @Test
    @DisplayName("Should return valid new average from negative value")
    void newAverageGivenNegativeValue(){
        //arrange
        Integer noteFromUser = -3;
        Double newValidAverage = 4.1;
        //act
        Double average = rating.computeNewAverage(noteFromUser);
        //assert
        assertEquals(newValidAverage, average);
    }
}
