package com.infoshareacademy;

import java.util.Properties;

public class AppProperties {

    private final Properties properties;

    public AppProperties(Properties properties){
        this.properties = properties;
    }

    public String getCity(){
        return properties.getProperty("city");
    }

    public String getFilePath(){
        return properties.getProperty("filePath");
    }

    public String getDriversList(){ return properties.getProperty("driversList");}
}
