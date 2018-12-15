package com.infoshareacademy.usersengine.model;

import org.apache.commons.math3.util.Precision;

import javax.persistence.*;

import static java.lang.StrictMath.abs;

@Entity
@Table(name = "mapsratings")
public class MapsRating {
    private static final Double STARTING_AVERAGE = 0.0;
    private static final Integer STARTING_PERSONS = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "average")
    private Double average;

    @Column(name = "persons")
    private Integer persons;


    public MapsRating() {
    }

    public MapsRating(Double average, Integer persons) {
        this.average = average;
        this.persons = persons;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public double computeNewAverage(Integer note) {
        Double result = (this.average * this.persons + abs(note)) / (this.persons + 1);
        return Precision.round(result, 1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
