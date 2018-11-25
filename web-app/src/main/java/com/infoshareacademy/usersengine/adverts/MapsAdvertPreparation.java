package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.usersengine.model.MapsAdvert;

import java.util.List;
import java.util.Map;

public interface MapsAdvertPreparation {

    MapsAdvert buildMapsAdvert(Map<String, String[]> parameters);
    List<String> getValidationSummary();

}
