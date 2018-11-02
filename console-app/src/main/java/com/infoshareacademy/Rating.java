package com.infoshareacademy;

import org.apache.commons.math3.util.Precision;

public class Rating {

    private static final Double STARTING_AVERAGE = 0.0;
    private static final Integer STARTING_PERSONS = 0;
    private Double average;
    private Integer persons;

    public Rating() {
        this.average = STARTING_AVERAGE;
        this.persons = STARTING_PERSONS;
    }

    public Rating(Double average, Integer persons) {
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

    public double newAverage(Integer note) {
        Double result = (this.average * this.persons + note) / (this.persons + 1);
        return Precision.round(result, 1);
    }

    @Override
    public String toString() {
        return "Average Rating: " + average +
                "/5.0  Number of ratings: " + persons;
    }

}
