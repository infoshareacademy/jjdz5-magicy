package com.infoshareacademy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    @DisplayName("Should return new average from note given by user")
    void returnNewValidAverageFromNoteGivenByUser() {
        Rating rating = new Rating();
        rating.setAverage(4.3);
        rating.setPersons(5);

        Integer noteFromUser = 3;
        Double newValidAverage = 4.1;

        Double average = rating.newAverage(noteFromUser);

        assertEquals(newValidAverage, average);
    }
}