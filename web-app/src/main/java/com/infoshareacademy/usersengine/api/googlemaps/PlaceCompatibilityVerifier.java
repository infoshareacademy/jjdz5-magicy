package com.infoshareacademy.usersengine.api.googlemaps;

import com.infoshareacademy.usersengine.adverts.AdvertData;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentResponse;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentType;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceResult;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.VerifierParameter;

import java.util.HashMap;
import java.util.Map;

public class PlaceCompatibilityVerifier {

    private PlacesClient client = new PlacesClientImpl();
    
    public Boolean arePlacesCompatible(AdvertData advertData) {
        return isPlaceCompatible(prepareStartPlaceData(advertData)) &&
               isPlaceCompatible(prepareEndPlaceData(advertData));
    }
    
    private Map<VerifierParameter, String> prepareStartPlaceData(AdvertData advertData) {
        Map<VerifierParameter, String> startData = new HashMap<>();
        startData.put(VerifierParameter.PLACE_ID, advertData.getStartMapsPointId());
        startData.put(VerifierParameter.STREET_NAME, advertData.getStartStreet());
        startData.put(VerifierParameter.STREET_NUMBER, advertData.getStartStreetNumber());
        startData.put(VerifierParameter.CITY, advertData.getStartCity());
        startData.put(VerifierParameter.LATITUDE, advertData.getStartLatitude());
        startData.put(VerifierParameter.LONGITUDE, advertData.getStartLongitude());
        return startData;
    }
    
    private Map<VerifierParameter, String> prepareEndPlaceData(AdvertData advertData) {
        Map<VerifierParameter, String> endData = new HashMap<>();
        endData.put(VerifierParameter.PLACE_ID, advertData.getEndMapsPointId());
        endData.put(VerifierParameter.STREET_NAME, advertData.getEndStreet());
        endData.put(VerifierParameter.STREET_NUMBER, advertData.getEndStreetNumber());
        endData.put(VerifierParameter.CITY, advertData.getEndCity());
        endData.put(VerifierParameter.LATITUDE, advertData.getEndLatitude());
        endData.put(VerifierParameter.LONGITUDE, advertData.getEndLongitude());
        return endData;
    }
    
    private Boolean isPlaceCompatible(Map<VerifierParameter, String> placeMap) {
        PlaceResult googlePlace = client.getPlaceById(placeMap.get(VerifierParameter.PLACE_ID));
        return  isStreetNameCompatible(googlePlace, placeMap) &&
                isStreetNumberCompatible(googlePlace, placeMap) &&
                isCityCompatible(googlePlace, placeMap) &&
                isLatitudeCompatible(googlePlace, placeMap) &&
                isLongitudeCompatible(googlePlace, placeMap);
    }
    
    private Boolean isStreetNameCompatible(PlaceResult googlePlace, 
                                           Map<VerifierParameter, String> placeMap) {
        String streetName = client.getSpecificAddressComponent(googlePlace, 
                PlaceAddressComponentType.STREET_NAME, PlaceAddressComponentResponse.FULL);
        return streetName.equals(placeMap.get(VerifierParameter.STREET_NAME));
    }
    
    private Boolean isStreetNumberCompatible(PlaceResult googlePlace, 
                                             Map<VerifierParameter, String> placeMap) {
        String streetNumber = client.getSpecificAddressComponent(googlePlace, 
                PlaceAddressComponentType.STREET_NUMBER, PlaceAddressComponentResponse.FULL);
        return streetNumber.equals(placeMap.get(VerifierParameter.STREET_NUMBER));
    }
    
    private Boolean isCityCompatible(PlaceResult googlePlace, 
                                     Map<VerifierParameter, String> placeMap) {
        String streetNumber = client.getSpecificAddressComponent(googlePlace, 
                PlaceAddressComponentType.CITY, PlaceAddressComponentResponse.FULL);
        return streetNumber.equals(placeMap.get(VerifierParameter.CITY));
    }
    
    private Boolean isLatitudeCompatible(PlaceResult googlePlace,
                                         Map<VerifierParameter, String> placeMap) {
        Double latitudeToVerify = Double.valueOf(placeMap.get(VerifierParameter.LATITUDE));
        return googlePlace.getGeometry().getLocation().getLatitude()
                .equals(latitudeToVerify);
    }
    
    private Boolean isLongitudeCompatible(PlaceResult googlePlace,
                                         Map<VerifierParameter, String> placeMap) {
        Double longitudeToVerify = Double.valueOf(placeMap.get(VerifierParameter.LONGITUDE));
        return googlePlace.getGeometry().getLocation().getLongitude()
                .equals(longitudeToVerify);
    }
    
}
