package com.infoshareacademy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class ReadPropertiesTest {

    @Test
    @DisplayName("should return valid properties")
    void returnValidProperties() {
        //arrange
        String validCityProperty = "Gdańsk";
        String validFilePathProperty = "console-app/src/main/resources/com/infoshareacademy/adverts.json";
        String validDriversListProperty = "console-app/src/main/resources/com/infoshareacademy/driver.json";
        //act
        ReadProperties readProperties = new ReadProperties();
        AppProperties appProperties = readProperties.loadProperties();
        //assert
        assertEquals(validCityProperty, appProperties.getCity());
        assertEquals(validFilePathProperty, appProperties.getFilePath());
        assertEquals(validDriversListProperty, appProperties.getDriversList());
    }
}
