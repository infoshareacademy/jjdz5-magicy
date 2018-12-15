package com.infoshareacademy.usersengine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "maps_waypoints")
public class MapsWaypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    @NotNull
    private Double latitude;

    @Column(name = "longitude")
    @NotNull
    private Double longitude;

    @Column(name = "stopover")
    @NotNull
    private Boolean stopover;

    @ManyToMany(mappedBy = "waypoints")
    private Set<MapsAdvert> mapsAdverts;

    public MapsWaypoint() {
    }

    public MapsWaypoint(Double latitude, Double longitude, Boolean stopover) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.stopover = stopover;
        this.mapsAdverts = new HashSet<>();
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

    public Boolean getStopover() {
        return stopover;
    }

    public void setStopover(Boolean stopover) {
        this.stopover = stopover;
    }

    public Set<MapsAdvert> getMapsAdverts() {
        return mapsAdverts;
    }

    public void setMapsAdverts(Set<MapsAdvert> mapsAdverts) {
        this.mapsAdverts = mapsAdverts;
    }
}
