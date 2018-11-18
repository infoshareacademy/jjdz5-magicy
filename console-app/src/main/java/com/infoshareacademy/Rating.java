package com.infoshareacademy;

import org.apache.commons.math3.util.Precision;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "average")
    private Double average;

    @Column(name = "persons")
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

    public Rating(){
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

    public double newAverage(Integer note){
        Double result = (this.average * this.persons + note)/(this.persons+1);
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
