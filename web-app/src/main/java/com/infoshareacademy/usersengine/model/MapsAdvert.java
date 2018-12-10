package com.infoshareacademy.usersengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "maps_adverts")
public class MapsAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @NotNull
    private MapsDriver driver;

    @ManyToOne
    @JoinColumn(name = "start_address_id")
    @NotNull
    private MapsAddress startAddress;
    
    @Column(name = "start_address_info")
    private String startAddressInfo;
    
    @Column(name = "end_address_info")
    private String endAddressInfo;

    @ManyToOne
    @JoinColumn(name = "end_address_id")
    @NotNull
    private MapsAddress endAddress;

    @Column(name = "start_time")
    @NotNull
    private LocalTime startTime;

    @Column(name = "end_time")
    @NotNull
    private LocalTime endTime;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    public MapsAdvert() {
    }

    public MapsAdvert(MapsDriver driver, MapsAddress startAddress, MapsAddress endAddress,
                      String startAddressInfo, String endAddressInfo,
                      LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.driver = driver;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.startAddressInfo = startAddressInfo;
        this.endAddressInfo = endAddressInfo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MapsDriver getDriver() {
        return driver;
    }

    public void setDriver(MapsDriver driver) {
        this.driver = driver;
    }

    public MapsAddress getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(MapsAddress startAddress) {
        this.startAddress = startAddress;
    }

    public MapsAddress getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(MapsAddress endAddress) {
        this.endAddress = endAddress;
    }

    public String getStartAddressInfo() {
        return startAddressInfo;
    }

    public void setStartAddressInfo(String startAddressInfo) {
        this.startAddressInfo = startAddressInfo;
    }

    public String getEndAddressInfo() {
        return endAddressInfo;
    }

    public void setEndAddressInfo(String endAddressInfo) {
        this.endAddressInfo = endAddressInfo;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapsAdvert that = (MapsAdvert) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(driver, that.driver) &&
                Objects.equals(startAddress, that.startAddress) &&
                Objects.equals(startAddressInfo, that.startAddressInfo) &&
                Objects.equals(endAddressInfo, that.endAddressInfo) &&
                Objects.equals(endAddress, that.endAddress) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, driver, startAddress, startAddressInfo, endAddressInfo, endAddress, startTime, endTime, date);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MapsAdvert{");
        sb.append("id=").append(id).append("\n");
        sb.append(", driver=").append(driver.getName()).append(driver.getSurname()).append("\n");
        sb.append(", startAddress=").append(startAddress).append("\n");
        sb.append(", endAddress=").append(endAddress).append("\n");
        sb.append(", startAddressInfo=").append(startAddressInfo).append("\n");
        sb.append(", endAddressInfo=").append(endAddressInfo).append("\n");
        sb.append(", startTime=").append(startTime).append("\n");
        sb.append(", endTime=").append(endTime).append("\n");
        sb.append(", date=").append(date).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
