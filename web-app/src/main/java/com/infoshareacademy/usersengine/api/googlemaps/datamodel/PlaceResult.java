package com.infoshareacademy.usersengine.api.googlemaps.datamodel;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceResult {

    @JsonProperty(value = "address_components")
    private List<PlaceAddressComponent> addressComponents;

    @JsonProperty(value = "formatted_address")
    private String formattedAddress;

    @JsonProperty(value = "place_id")
    private String placeId;

    private PlaceGeometry geometry;

    private String name;

    public List<PlaceAddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<PlaceAddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public PlaceGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(PlaceGeometry geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
