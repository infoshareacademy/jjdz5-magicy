package com.infoshareacademy;

import java.util.Date;

public class Advert {
    private Integer id;
    private Date date;
    private Driver driver;
    private Route route;
    private Boolean promo;


    public Advert(){
    }

    public Advert(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Advert(Integer id, Date date, Driver driver, Route route, Boolean promo) {
        this.id = id;
        this.date = date;
        this.driver = driver;
        this.route = route;
        this.promo = promo;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Boolean getPromo() {
        return promo;
    }

    public void setPromo(Boolean promo) {
        this.promo = promo;
    }


    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", date=" + date +
                ", driver=" + driver +
                ", route=" + route +
                ", promo=" + promo +
                '}';
    }
}

