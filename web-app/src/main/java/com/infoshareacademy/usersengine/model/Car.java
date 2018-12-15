package com.infoshareacademy.usersengine.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "license_plate", unique = true)
    @NotNull
    private String licensePlate;

    @Column(name = "car_brand")
    @NotNull
    private String carBrand;

    @Column(name = "car_model")
    @NotNull
    private String carModel;

    public Car(){
    }

    public Car(String licensePlate, String carBrand, String carModel) {
        this.licensePlate = licensePlate;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Car{");
        sb.append("licensePlate='").append(licensePlate).append('\'');
        sb.append(", carBrand='").append(carBrand).append('\'');
        sb.append(", carModel='").append(carModel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
