package com.infoshareacademy.usersengine.model;


import javax.persistence.*;

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

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "driver")
    Driver driver;

    @Column(name = "is_driver")
    boolean isDriver;

    public User() {
    }

    public User(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
