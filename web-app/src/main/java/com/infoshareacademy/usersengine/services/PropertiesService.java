package com.infoshareacademy.usersengine.services;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesService {

    private static final String PROPERTIES_FILEPATH = "WEB-INF/configuration/app.properties";
    public static final String PROPERTY_API_KEY = "API_KEY";
    private static Properties properties = new Properties();

    public static String getMapsApiKey() {
        return properties.getProperty(PROPERTY_API_KEY);
    }

    public static void loadProperties(ServletContext servletContext) {
        try {
            properties.load(servletContext.getResourceAsStream(PROPERTIES_FILEPATH));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find properties file");
        } catch (IOException e) {
            System.out.println("Cannot load properties file");
        }
    }

}
