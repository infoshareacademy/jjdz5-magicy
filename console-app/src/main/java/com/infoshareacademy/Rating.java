package com.infoshareacademy;

import org.apache.commons.math3.util.Precision;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "average")
    private Double average;

    @Column(name = "persons")
    private Integer persons;

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

    public Rating(Double average, Integer persons) {
        this.average = average;
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Average Rating: " + average +
                "/5.0  Number of ratings: " + persons;
    }

    public Rating(){

    }
}
