package com.infoshareacademy;

import javax.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    public Driver(String name, String surname, String phone, String city, String district, Rating rating, Integer id) {
        super(name, surname, phone, rating);
        this.city = city;
        this.district = district;
        this.id = id;
    }

    public Driver(){
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getId() {
        return id;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
