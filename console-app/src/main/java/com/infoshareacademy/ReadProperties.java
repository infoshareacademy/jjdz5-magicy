package com.infoshareacademy;

import java.io.*;
import java.util.Properties;

public class ReadProperties {
    private final AppProperties appProperties = loadProperties();

    public static AppProperties loadProperties() {

        Properties properties = new Properties();

        try(InputStream resourceAsStream = AppProperties.class.getResourceAsStream("config.properties")) {
            properties.load(resourceAsStream);

        } catch (IOException e) {
            e.printStackTrace();

        }
        return new AppProperties(properties);
    }

    public String readCity(){
        return appProperties.getCity();
    }

    public String readFilePath(){
        return appProperties.getFilePath();
    }

    public String readDriversList(){ return appProperties.getDriversList();}
}
