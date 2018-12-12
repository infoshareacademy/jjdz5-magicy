package com.infoshareacademy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Route {

    private long id;

    private Date date;

    private String startCity;

    private String startStreet;

    private String endCity;

    private String endStreet;

    private String pickUpCity;

    private String pickUpStreet;

    private String startTime;

    private String endTime;

    private String pickUpTime;

    public Route(){

    }


    public Route(long id, Date date, String startCity, String startStreet, String endCity,
                 String endStreet, String pickUpCity, String pickUpStreet, String startTime, String endTime, String pickUpTime) {
        this.id =id;
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


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
