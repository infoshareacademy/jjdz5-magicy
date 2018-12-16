package com.infoshareacademy.usersengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maps_drivers")
public class MapsDriver {

    private static final Double START_RATING_VALUE = 5.0;
    private static final Integer START_RATINGS_QUANTITY_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "car_id", unique = true)
    private Car car;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MapsAdvert> mapAdverts;

    @Column(name = "average_rating")
    @NotNull
    private Double averageRating;

    @Column(name = "ratings_quantity")
    @NotNull
    private Integer ratingsQuantity;

    @Column(name = "rating_sum")
    @NotNull
    private Integer ratingSum;

    public MapsDriver() {
    }

    public MapsDriver(String name, String surname, String phoneNumber, Car car) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.car = car;
        this.mapAdverts = new ArrayList<>();
        this.averageRating = START_RATING_VALUE;
        this.ratingsQuantity = START_RATINGS_QUANTITY_VALUE;
        this.ratingSum = START_RATING_VALUE.intValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<MapsAdvert> getMapAdverts() {
        return mapAdverts;
    }

    public void setMapAdverts(List<MapsAdvert> mapAdverts) {
        this.mapAdverts = mapAdverts;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingsQuantity() {
        return ratingsQuantity;
    }

    public void setRatingsQuantity(Integer ratingsQuantity) {
        this.ratingsQuantity = ratingsQuantity;
    }

    public Integer getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(Integer ratingSum) {
        this.ratingSum = ratingSum;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MapsDriver{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", car=").append(car);
        sb.append(", mapAdverts=").append(mapAdverts);
        sb.append('}');
        return sb.toString();
    }
}