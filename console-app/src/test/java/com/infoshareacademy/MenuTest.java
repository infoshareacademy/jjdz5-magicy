package com.infoshareacademy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    @DisplayName("Should return value EXIT of enum state from number given by user")
    void returnExitFromNumberGivenByUser() {
        String numberFromUser = "0";
        State validEnum = State.EXIT;

        InputStream in = new ByteArrayInputStream(numberFromUser.getBytes());
        System.setIn(in);

        Menu menuMethod = new Menu();
        menuMethod.menu();

        assertEquals(validEnum, menuMethod.state);
    }
}