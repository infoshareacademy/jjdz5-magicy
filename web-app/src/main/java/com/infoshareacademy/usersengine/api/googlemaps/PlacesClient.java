package com.infoshareacademy.usersengine.api.googlemaps;

import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentResponse;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentType;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceResult;

public interface PlacesClient {

    String BASE_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    String API_KEY_PARAMETER = "?key=";
    String PLACE_ID_PARAMETER = "&placeid=";
    String CORRECT_RESPONSE_STATUS = "OK";

    PlaceResult getPlaceById(String id);
    String getSpecificAddressComponent(PlaceResult result, PlaceAddressComponentType type,
                                       PlaceAddressComponentResponse response);

}
