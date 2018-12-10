package com.infoshareacademy.usersengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "maps_addresses")
public class MapsAddress {

    @Id
    @Column(name = "id", unique = true)
    @NotNull
    private String mapsPointId;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "street_name")
    @NotNull
    private String streetName;

    @Column(name = "street_number")
    @NotNull
    private String streetNumber;

    @Column(name = "formatted_address")
    @NotNull
    private String formattedAddress;

    @Column(name = "latitude")
    @NotNull
    private Double latitude;

    @Column(name = "longitude")
    @NotNull
    private Double longitude;

    @OneToMany(mappedBy = "startAddress", fetch = FetchType.LAZY)
    private Set<MapsAdvert> starting;

    @OneToMany(mappedBy = "endAddress", fetch = FetchType.LAZY)
    private Set<MapsAdvert> ending;

    public MapsAddress() {
    }

    public MapsAddress(String mapsPointId, String city, String streetName, String streetNumber,
                       String formattedAddress, Double latitude, Double longitude) {
        this.mapsPointId = mapsPointId;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.formattedAddress = formattedAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getMapsPointId() {
        return mapsPointId;
    }

    public void setMapsPointId(String mapsPointId) {
        this.mapsPointId = mapsPointId;
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

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
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

    public Set<MapsAdvert> getStarting() {
        return starting;
    }

    public void setStarting(Set<MapsAdvert> starting) {
        this.starting = starting;
    }

    public Set<MapsAdvert> getEnding() {
        return ending;
    }

    public void setEnding(Set<MapsAdvert> ending) {
        this.ending = ending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapsAddress that = (MapsAddress) o;
        return Objects.equals(mapsPointId, that.mapsPointId) &&
                Objects.equals(city, that.city) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(streetNumber, that.streetNumber) &&
                Objects.equals(formattedAddress, that.formattedAddress) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(starting, that.starting) &&
                Objects.equals(ending, that.ending);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mapsPointId, city, streetName, streetNumber, formattedAddress, latitude, longitude, starting, ending);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MapsAddress{");
        sb.append(" mapsPointId='").append(mapsPointId).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append(", streetNumber='").append(streetNumber).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
