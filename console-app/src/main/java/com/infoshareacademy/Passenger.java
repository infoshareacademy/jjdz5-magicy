package com.infoshareacademy;

public class Passenger extends User{
    private Integer idPassenger;


    public Passenger(String name, String surname, String phone, Rating rating, Integer idPassenger) {
        super(name, surname, phone, rating);
        this.idPassenger = idPassenger;
    }

    public Passenger() {

    }

    public Integer getIdPassenger() {
        return idPassenger;
    }
}
