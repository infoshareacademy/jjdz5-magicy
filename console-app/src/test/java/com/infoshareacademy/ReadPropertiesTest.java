package com.infoshareacademy;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class ReadPropertiesTest {

    @Test
    @DisplayName("should return valid properties")
    void returnValidProperties() {
        String validCityProperty = "Gdańsk";
        String validFilePathProperty = "console-app/src/main/resources/com/infoshareacademy/adverts.json";
        String validDriversListProperty = "console-app/src/main/resources/com/infoshareacademy/driver.json";

        ReadProperties readProperties = new ReadProperties();
        AppProperties appProperties = readProperties.loadProperties();

        assertEquals(validCityProperty, appProperties.getCity());
        assertEquals(validFilePathProperty, appProperties.getFilePath());
        assertEquals(validDriversListProperty, appProperties.getDriversList());
    }
}
