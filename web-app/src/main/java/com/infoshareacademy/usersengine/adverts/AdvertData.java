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

    private String pickUpCity;
    private String pickUpStreet;
    private String pickUpTime;
    @NotNull @NotEmpty
    private String date;

    private String startStreetNumber;
    private String startLatitude;
    private String startLongitude;
    private String startMapsPointId;
    private String startInfo;
    private String endStreetNumber;
    private String endLatitude;
    private String endLongitude;
    private String endMapsPointId;
    private String endInfo;
    private String driverId;

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

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStartMapsPointId() {
        return startMapsPointId;
    }

    public void setStartMapsPointId(String startMapsPointId) {
        this.startMapsPointId = startMapsPointId;
    }

    public String getStartStreetNumber() {
        return startStreetNumber;
    }

    public void setStartStreetNumber(String startStreetNumber) {
        this.startStreetNumber = startStreetNumber;
    }

    public String getEndStreetNumber() {
        return endStreetNumber;
    }

    public void setEndStreetNumber(String endStreetNumber) {
        this.endStreetNumber = endStreetNumber;
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getEndMapsPointId() {
        return endMapsPointId;
    }

    public void setEndMapsPointId(String endMapsPointId) {
        this.endMapsPointId = endMapsPointId;
    }

    public String getStartInfo() {
        return startInfo;
    }

    public void setStartInfo(String startInfo) {
        this.startInfo = startInfo;
    }

    public String getEndInfo() {
        return endInfo;
    }

    public void setEndInfo(String endInfo) {
        this.endInfo = endInfo;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdvertData{");
        sb.append("startCity='").append(startCity).append('\'');
        sb.append(", startStreet='").append(startStreet).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endCity='").append(endCity).append('\'');
        sb.append(", endStreet='").append(endStreet).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", pickUpCity='").append(pickUpCity).append('\'');
        sb.append(", pickUpStreet='").append(pickUpStreet).append('\'');
        sb.append(", pickUpTime='").append(pickUpTime).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", startStreetNumber='").append(startStreetNumber).append('\'');
        sb.append(", startLatitude='").append(startLatitude).append('\'');
        sb.append(", startLongitude='").append(startLongitude).append('\'');
        sb.append(", startMapsPointId='").append(startMapsPointId).append('\'');
        sb.append(", startInfo='").append(startInfo).append('\'');
        sb.append(", endStreetNumber='").append(endStreetNumber).append('\'');
        sb.append(", endLatitude='").append(endLatitude).append('\'');
        sb.append(", endLongitude='").append(endLongitude).append('\'');
        sb.append(", endMapsPointId='").append(endMapsPointId).append('\'');
        sb.append(", endInfo='").append(endInfo).append('\'');
        sb.append(", driverId='").append(driverId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}