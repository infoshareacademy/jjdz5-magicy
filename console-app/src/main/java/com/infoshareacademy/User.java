package com.infoshareacademy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NamedQueries({
@NamedQuery(
        name ="findUserByEmail",
        query = "SELECT u FROM User u WHERE u.userEmail = :param"
        )
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "email")
    @NotNull
    private String userEmail;

    @Column(name = "phone")
    @NotNull
    private String phone;

    @OneToOne
    @JoinColumn(name = "rating_id", unique = true)
    private Rating rating;

    @Column(name = "password")
    private String password;

    public User(String name, String surname, String phone, Rating rating) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.rating = rating;
    }

    public User(String name, String surname, String phone, Rating rating, Long id) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.rating = rating;
        this.id = id;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", rating=" + rating +
                '}';
    }
}
