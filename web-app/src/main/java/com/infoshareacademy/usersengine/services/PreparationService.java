package com.infoshareacademy.usersengine.services;

import java.util.Map;

public class PreparationService {

    private static final Integer PARAMETER_INDEX = 0;

    public static String getSpecificParameter (Map<String, String[]> map, String parameter) {
        return map.get(parameter)[PARAMETER_INDEX].trim();
    }

}
