package com.infoshareacademy;

import org.apache.commons.math3.util.Precision;
import static java.lang.StrictMath.abs;
import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    private static final Double STARTING_AVERAGE = 0.0;
    private static final Integer STARTING_PERSONS = 0;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "average")
    private Double average;
  
    @Column(name = "persons")
    private Integer persons;

    public Rating(Double average, Integer persons) {
        this.average = average;
        this.persons = persons;
    }

    public Rating(Double average, Integer persons, Integer id) {
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

    public Integer getId() {
        return id;
    }
  
    @Override
    public String toString() {
        return "Average Rating: " + average +
                "/5.0  Number of ratings: " + persons;
    }
}
