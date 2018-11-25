package com.infoshareacademy.usersengine.services;

import java.util.Map;

public class ParametersService {

    private static final Integer PARAMETER_INDEX = 0;

    public static Boolean isParameterPresent (Map<String, String[]> map, String parameter) {
        return map.keySet().contains(parameter);
    }

    public static String getSpecificParameter (Map<String, String[]> map, String parameter) {
        try {
            return map.get(parameter)[PARAMETER_INDEX].trim();
        } catch (NullPointerException e) {
            return "";
        }
    }

}
