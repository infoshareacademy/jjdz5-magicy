package com.infoshareacademy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class Advert {

    private Long id;

    private Date date;

    private Driver driver;

    private Route route;

    private Boolean promo;

    public Advert(){
        promo = false;
    }


    public Advert(Long id, Date date, Driver driver, Route route) {
        this.id = id;
        this.date = date;
        this.driver = driver;
        this.route = route;
        promo = false;
    }

    public Driver getDriver() {
        return driver;
    }

    public Route getRoute() {
        return route;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
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
                '}';
    }
}

