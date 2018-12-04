package com.infoshareacademy.usersengine.services.mapsadverts;

import java.util.List;
import java.util.Map;

public interface MapsAdvertVerifier {

    void verifyParameters(Map<String, String[]> parameters);
    Boolean areParametersCorrect();
    List<String> getErrorMessages();

}
