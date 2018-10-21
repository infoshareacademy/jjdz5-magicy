package com.infoshareacademy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "add_at")
    @NotNull
    private Date date;

    @OneToOne

    @Column(name = "driver_id")
    @NotNull
    private Driver driver;

    @Column(name = "route_id")
    @NotNull
    private Route route;

    @Column(name = "promo")
    private Boolean promo;

    public Advert(){
        promo = false;
    }


    public Advert(Integer id, Date date, Driver driver, Route route) {
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
                '}';
    }
}

