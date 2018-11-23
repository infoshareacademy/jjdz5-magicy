package com.infoshareacademy.usersengine.api.googlemaps.datamodel;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlaceLocation {

    @JsonProperty(value = "lat")
    private Double latitude;

    @JsonProperty(value = "lng")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
