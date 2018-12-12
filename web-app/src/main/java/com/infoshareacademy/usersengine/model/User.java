package com.infoshareacademy.usersengine.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name ="findUserByEmail",
                query = "SELECT u FROM User u WHERE u.email = :param"
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @OneToOne
   // @Column(name = "driver")
    private MapsDriver driver;

    @Column(name = "is_driver")
    boolean isDriver;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public MapsDriver getDriver() {
        return driver;
    }

    public void setDriver(MapsDriver driver) {
        this.driver = driver;
    }
}
