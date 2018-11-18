package com.infoshareacademy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    @DisplayName("Should return valid enum value from number given by user ")
    void returnValidEnumValueFromNumberGivenFromUser() {
        //arrange
        int enumValueNumber = 2;
        State validState = State.ADD_ADVERT;
        //act
        State stateFromUser = State.get(enumValueNumber);
        //assert
        assertEquals(validState, stateFromUser);
    }
}
