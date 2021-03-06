package com.infoshareacademy;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "rating_id", unique = true)
    private Rating rating;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @OneToMany(mappedBy = "advert", fetch = FetchType.LAZY)
    private List<Advert> adverts;


    public Driver(String name, String surname, String phone, String city, String district, Rating rating, Long id) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.rating = rating;
        this.city = city;
        this.district = district;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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

    public Long getId() {
        return id;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Driver{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", adverts=").append(adverts.stream().map(Advert::getId).collect(toList()));
        sb.append('}');
        return sb.toString();
    }
}