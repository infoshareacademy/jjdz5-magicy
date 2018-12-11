package com.infoshareacademy.usersengine.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesService {

    private static final String PROPERTIES_FILEPATH = "WEB-INF/properties/webapp.properties";
    private static final Integer DEFAULT_MAX_PERIOD_DAYS = 30;
    private static final Integer DEFAULT_MIN_HOURS_TO_START = 2;
    private static final Integer DEFAULT_REDIRECTION_DELAY = 3;
    private static final Properties PROPERTIES = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesService.class);

    public static String getMapsApiKey() {
        return PROPERTIES.getProperty(Property.API_KEY.name());
    }

    public static Integer getRedirectionDelay() {
        return parseNumericValue(Property.REDIRECTION_DELAY, DEFAULT_REDIRECTION_DELAY);
    }

    public static Integer getAdvertMaxPeriodDays() {
        return parseNumericValue(Property.ADVERT_MAX_PERIOD_DAYS, DEFAULT_MAX_PERIOD_DAYS);
    }

    public static Integer getAdvertMinHoursToStart() {
        return parseNumericValue(Property.ADVERT_MIN_HOURS_TO_START, DEFAULT_MIN_HOURS_TO_START);
    }

    public static String getMsgAdvertAddOk() {
        return PROPERTIES.getProperty(Property.MSG_ADVERT_ADD_OK.name());
    }

    public static String getMsgBadMainStartAddress() {
        return PROPERTIES.getProperty(Property.MSG_BAD_MAIN_START_ADDRESS.name());
    }

    public static String getMsgBadMainEndAddress() {
        return PROPERTIES.getProperty(Property.MSG_BAD_MAIN_END_ADDRESS.name());
    }

    public static String getMsgBadDetailStartAddress() {
        return PROPERTIES.getProperty(Property.MSG_BAD_DETAIL_START_ADDRESS.name());
    }

    public static String getMsgBadDetailEndAddress() {
        return PROPERTIES.getProperty(Property.MSG_BAD_DETAIL_END_ADDRESS.name());
    }

    public static String getMsgEqualAddresses() {
        return PROPERTIES.getProperty(Property.MSG_EQUAL_ADDRESSES.name());
    }

    public static String getMsgBadStartTime() {
        return PROPERTIES.getProperty(Property.MSG_BAD_START_TIME.name());
    }

    public static String getMsgBadEndTime() {
        return PROPERTIES.getProperty(Property.MSG_BAD_END_TIME.name());
    }

    public static String getMsgEarlyStartTime() {
        return PROPERTIES.getProperty(Property.MSG_EARLY_START_TIME.name());
    }

    public static String getMsgStartAfterEndTime() {
        return PROPERTIES.getProperty(Property.MSG_START_AFTER_END_TIME.name());
    }

    public static String getMsgBadDate() {
        return PROPERTIES.getProperty(Property.MSG_BAD_DATE.name());
    }

    public static String getMsgBadDriver() {
        return PROPERTIES.getProperty(Property.MSG_BAD_DRIVER.name());
    }

    public static String getMsgBadCar() {
        return PROPERTIES.getProperty(Property.MSG_BAD_CAR.name());
    }

    public static void loadProperties(ServletContext servletContext) {
        try {
            PROPERTIES.load(servletContext.getResourceAsStream(PROPERTIES_FILEPATH));
        } catch (FileNotFoundException e) {
            LOG.error("Cannot find properties file");
        } catch (IOException e) {
            LOG.error("Cannot load properties file");
        }
    }

    private static Integer parseNumericValue(Property property, Integer defaultValue) {
        Integer value;
        try {
            value = Integer.valueOf(PROPERTIES.getProperty(property.name()));
        } catch (NumberFormatException e) {
            value = defaultValue;
        }
        return value;
    }

}
