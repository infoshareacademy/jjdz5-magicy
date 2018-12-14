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

    @Column(name = "is_driver")
    private boolean isDriver;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToOne
    @JoinColumn(name = "driver_id", unique = true)
    private MapsDriver driver;

    public User() {
    }

    public User(String email, MapsDriver driver, boolean isDriver, boolean isAdmin) {
        this.email = email;
        this.driver = driver;
        this.isDriver = isDriver;
        this.isAdmin = isAdmin;
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

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setDriver(MapsDriver driver) {
        this.driver = driver;
    }
}
