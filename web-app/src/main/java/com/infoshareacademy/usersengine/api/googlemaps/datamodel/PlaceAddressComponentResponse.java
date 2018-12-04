package com.infoshareacademy.usersengine.api.googlemaps.datamodel;

public enum PlaceAddressComponentResponse {

    FULL("long_name"),
    SHORT("short_name");

    private String componentResponse;

    PlaceAddressComponentResponse(String componentResponse) {
        this.componentResponse = componentResponse;
    }

    public String getComponentResponse() {
        return componentResponse;
    }
}
