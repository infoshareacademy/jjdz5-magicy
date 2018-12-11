package com.infoshareacademy.usersengine.services.mapsadverts;

import java.util.List;
import java.util.Map;

public interface MapsAdvertProcessing {

    void processMapsAdvertCreation(Map<String, String[]> parameters);
    Long getBuildedAdvertId();
    List<String> getSummary();

}
