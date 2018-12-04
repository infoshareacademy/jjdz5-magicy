package com.infoshareacademy.usersengine.services.mapsadverts;

import java.util.List;
import java.util.Map;

public interface MapsAdvertProcessing {

    void processMapsAdvert(Map<String, String[]> parameters);
    List<String> getSummary();

}
