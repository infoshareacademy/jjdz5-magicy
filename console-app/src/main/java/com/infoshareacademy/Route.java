package com.infoshareacademy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "start_at")
    @NotNull
    private Date date;

    @Column(name = "start_city")
    @NotNull
    private String startCity;

    @Column(name = "start_street")
    @NotNull
    private String startStreet;

    @Column(name = "end_city")
    @NotNull
    private String endCity;

    @Column(name = "ent_street")
    @NotNull
    private String endStreet;

    @Column(name = "pick_up_city")
    private String pickUpCity;

    @Column(name = "pick_up_street")
    private String pickUpStreet;

    @Column(name = "start_time")
    @NotNull
    private String startTime;

    @Column(name = "end_time")
    @NotNull
    private String endTime;

    @Column(name = "pick_up_time")
    private String pickUpTime;

    public Route(){

    }


    public Route(Integer id, Date date, String startCity, String startStreet, String endCity,
                 String endStreet, String pickUpCity, String pickUpStreet, String startTime, String endTime, String pickUpTime) {
        this.id = id;
        this.date = date;
        this.startCity = startCity;
        this.startStreet = startStreet;
        this.endCity = endCity;
        this.endStreet = endStreet;
        this.pickUpCity = pickUpCity;
        this.pickUpStreet = pickUpStreet;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pickUpTime = pickUpTime;
    }

    public Integer getId() {
        return id;
    }

    public String getStartStreet() {
        return startStreet;
    }

    public String getEndStreet() {
        return endStreet;
    }

    public String getPickUpStreet() {
        return pickUpStreet;
    }

    public Date getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartCity() {
        return startCity;
    }
    public String getEndCity() {
        return endCity;
    }
    public String getPickUpCity() {
        return pickUpCity;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public void setStartStreet(String startStreet) {
        this.startStreet = startStreet;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public void setEndStreet(String endStreet) {
        this.endStreet = endStreet;
    }

    public void setPickUpCity(String pickUpCity) {
        this.pickUpCity = pickUpCity;
    }

    public void setPickUpStreet(String pickUpStreet) {
        this.pickUpStreet = pickUpStreet;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", date=" + date +
                ", startCity='" + startCity + '\'' +
                ", startStreet='" + startStreet + '\'' +
                ", endCity='" + endCity + '\'' +
                ", endStreet='" + endStreet + '\'' +
                ", pickUpCity='" + pickUpCity + '\'' +
                ", pickUpStreet='" + pickUpStreet + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                '}';
    }
}
