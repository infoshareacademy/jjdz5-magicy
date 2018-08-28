package com.infoshareacademy;

import java.util.Date;

class Advert {
    private Integer id;
    private Date date;
    private Driver driver;
    private Route route;

    public Advert(){
    }

    public Advert(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Advert(Integer id, Date date, Driver driver, Route route) {
        this.id = id;
        this.date = date;
        this.driver = driver;
        this.route = route;
    }

    public Driver getDriver() {
        return driver;
    }

    public Route getRoute() {
        return route;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", date=" + date +
                ", driver=" + driver +
                ", route=" + route +
                '}';
    }

}

