package com.infoshareacademy.usersengine.services.maps_adverts;

import java.util.List;
import java.util.Map;

public interface MapsAdvertProcessing {

    void processMapsAdvert(Map<String, String[]> parameters);
    List<String> getSummary();

}
