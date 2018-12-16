package com.infoshareacademy.usersengine.api.googlemaps.datamodel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceRestResponse {

    private PlaceResult result;

    private String status;

    public PlaceResult getResult() {
        return result;
    }

    public void setResult(PlaceResult result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
