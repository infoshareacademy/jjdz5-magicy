package com.infoshareacademy.usersengine.services;

import java.util.Map;

public class ParametersService {

    private static final Integer PARAMETER_INDEX = 0;

    public static Boolean isParameterCorrect(Map<String, String[]> map, String parameter) {
        Boolean isParameterCorrect = false;
        if (isParameterPresent(map, parameter)) {
            isParameterCorrect = isParameterNotEmpty(map, parameter);
        }
        return isParameterCorrect;
    }

    private static Boolean isParameterPresent(Map<String, String[]> map, String parameter) {
        return map.keySet().contains(parameter);
    }

    private static Boolean isParameterNotEmpty(Map<String, String[]> map, String parameter) {
        return !map.get(parameter)[PARAMETER_INDEX].isEmpty();
    }

    public static String getSpecificParameter(Map<String, String[]> map, String parameter) {
        try {
            return map.get(parameter)[PARAMETER_INDEX].trim();
        } catch (NullPointerException e) {
            return "";
        }
    }

}
