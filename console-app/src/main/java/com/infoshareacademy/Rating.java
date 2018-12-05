package com.infoshareacademy;

import org.apache.commons.math3.util.Precision;
import static java.lang.StrictMath.abs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Rating {
    private static final Double STARTING_AVERAGE = 0.0;
    private static final Integer STARTING_PERSONS = 0;

    private Long id;

    private Double average;

    private Integer persons;

    public Rating(Double average, Integer persons) {
        this.average = average;
        this.persons = persons;
    }

    public Rating(Double average, Integer persons, Long id) {
        this.average = average;
        this.persons = persons;
        this.id = id;
    }

    public Rating() {
        this.average = STARTING_AVERAGE;
        this.persons = STARTING_PERSONS;
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

    public double computeNewAverage(Integer note){
        Double result = (this.average * this.persons + abs(note))/(this.persons+1);
        return Precision.round(result, 1);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Average Rating: " + average +
                "/5.0  Number of ratings: " + persons;
    }
}
