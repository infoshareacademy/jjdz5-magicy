package com.infoshareacademy.usersengine.adverts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdvertData {
    @NotNull @NotEmpty
    private String startCity;
    @NotNull @NotEmpty
    private String startStreet;
    @NotNull @NotEmpty
    private String startTime;
    @NotNull @NotEmpty
    private String endCity;
    @NotNull @NotEmpty
    private String endStreet;
    @NotNull @NotEmpty
    private String endTime;
    @NotNull @NotEmpty
    private String pickUpCity;
    private String pickUpStreet;
    private String pickUpTime;
    @NotNull @NotEmpty
    private String date;

    @Override
    public String toString() {
        return "AdvertData{" +
                "startCity='" + startCity + '\'' +
                ", startStreet='" + startStreet + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endCity='" + endCity + '\'' +
                ", endStreet='" + endStreet + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pickUpCity='" + pickUpCity + '\'' +
                ", pickUpStreet='" + pickUpStreet + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public AdvertData() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartStreet() {
        return startStreet;
    }

    public void setStartStreet(String startStreet) {
        this.startStreet = startStreet;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getEndStreet() {
        return endStreet;
    }

    public void setEndStreet(String endStreet) {
        this.endStreet = endStreet;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPickUpCity() {
        return pickUpCity;
    }

    public void setPickUpCity(String pickUpCity) {
        this.pickUpCity = pickUpCity;
    }

    public String getPickUpStreet() {
        return pickUpStreet;
    }

    public void setPickUpStreet(String pickUpStreet) {
        this.pickUpStreet = pickUpStreet;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
}