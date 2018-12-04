package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.model.MapsAddress;

import java.util.Map;

public interface MapsAddressBuilder {

    MapsAddress buildStartMapsAddress(Map<String, String[]> parameters);
    MapsAddress buildEndMapsAddress(Map<String, String[]> parameters);


}
