package com.infoshareacademy.usersengine.api.googlemaps.datamodel;

public enum PlaceAddressComponentType {

    STREET_NAME("route"),
    STREET_NUMBER("street_number"),
    CITY("locality");

    private String componentType;

    PlaceAddressComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentType() {
        return componentType;
    }
}
