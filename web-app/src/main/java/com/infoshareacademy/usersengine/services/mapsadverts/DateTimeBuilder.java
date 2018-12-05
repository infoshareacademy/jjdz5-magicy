package com.infoshareacademy.usersengine.services.mapsadverts;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface DateTimeBuilder {

    LocalTime buildStartTime(Map<String, String[]> parameters);
    LocalTime buildEndTime(Map<String, String[]> parameters);
    LocalDate buildDate(Map<String, String[]> parameters);

}
