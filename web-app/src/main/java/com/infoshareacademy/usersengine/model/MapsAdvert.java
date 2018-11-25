package com.infoshareacademy.usersengine.model;

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
import java.time.LocalDate;
import java.time.LocalTime;

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

    @OneToOne
    @JoinColumn(name = "start_address_id", unique = true)
    @NotNull
    private MapsAddress startAddress;

    @OneToOne
    @JoinColumn(name = "end_address_id", unique = true)
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
                      LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.driver = driver;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
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
    public String toString() {
        final StringBuffer sb = new StringBuffer("MapsAdvert{");
        sb.append("id=").append(id);
        sb.append(", driver=").append(driver);
        sb.append(", startAddress=").append(startAddress);
        sb.append(", endAddress=").append(endAddress);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
