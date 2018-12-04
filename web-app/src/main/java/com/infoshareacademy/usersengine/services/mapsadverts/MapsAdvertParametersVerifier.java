package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.model.AdvertPartType;
import com.infoshareacademy.usersengine.model.AdvertOverallType;

import java.util.Map;

public interface MapsAdvertParametersVerifier {

    Boolean isAddressParameterCorrect(Map<String, String[]> parameters, AdvertPartType type);
    Boolean isTimeParameterCorrect(Map<String, String[]> parameters, AdvertPartType type);
    Boolean isOverallParameterCorrect(Map<String, String[]> parameters, AdvertOverallType type);
    Boolean isAddressPrecisionParameterCorrect(Map<String, String[]> parameters, AdvertPartType type);

}
