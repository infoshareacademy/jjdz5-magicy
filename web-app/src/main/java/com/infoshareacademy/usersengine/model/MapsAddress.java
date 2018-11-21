package com.infoshareacademy.usersengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "maps_addresses")
public class MapsAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "street_name")
    @NotNull
    private String streetName;

    @Column(name = "street_number")
    @NotNull
    private String streetNumber;

    @Column(name = "latitude")
    @NotNull
    private Double latitude;

    @Column(name = "longitude")
    @NotNull
    private Double longitude;

    @Column(name = "map_id")
    @NotNull
    private String addressMapId;

    @Column(name = "info")
    private String info;

    public MapsAddress() {
    }

    public MapsAddress(String city, String streetName, String streetNumber, Double latitude,
                       Double longitude, String addressMapId, String info) {
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressMapId = addressMapId;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

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

    public String getAddressMapId() {
        return addressMapId;
    }

    public void setAddressMapId(String addressMapId) {
        this.addressMapId = addressMapId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MapsAddress{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append(", streetNumber='").append(streetNumber).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", addressMapId='").append(addressMapId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
