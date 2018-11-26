package com.infoshareacademy.usersengine.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesService {

    private static final String PROPERTIES_FILEPATH = "WEB-INF/properties/webapp.properties";
    private static final Integer DEFAULT_PERIOD = 30;
    private static final Properties PROPERTIES = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesService.class);

    public static String getMapsApiKey() {
        return PROPERTIES.getProperty(Property.API_KEY.name());
    }

    public static Integer getAdvertPeriodDays() {
        Integer period;
        try {
            period = Integer.valueOf(PROPERTIES.getProperty(Property.ADVERT_PERIOD_DAYS.name()));
        } catch (NumberFormatException e) {
            period = DEFAULT_PERIOD;
        }
        return period;
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

    public static String getMsgBadStartTime() {
        return PROPERTIES.getProperty(Property.MSG_BAD_START_TIME.name());
    }

    public static String getMsgBadEndTime() {
        return PROPERTIES.getProperty(Property.MSG_BAD_END_TIME.name());
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

}
