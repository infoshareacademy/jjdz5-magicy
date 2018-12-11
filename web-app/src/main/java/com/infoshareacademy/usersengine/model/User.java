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
    MapsDriver driver;
    @Column(name = "is_driver")
    boolean isDriver;
    @Column(name = "is_admin")
    boolean isAdmin;
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
    public MapsDriver getDriver() {
        return driver;
    }
    public void setDriver(MapsDriver driver) {
        this.driver = driver;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User(Long id, String userEmail, MapsDriver driver, boolean isDriver, boolean isAdmin) {
        this.id = id;
        this.userEmail = userEmail;
        this.driver = driver;
        this.isDriver = isDriver;
        this.isAdmin = isAdmin;
    }

    public User(String userEmail, MapsDriver driver, boolean isDriver, boolean isAdmin) {
        this.userEmail = userEmail;
        this.driver = driver;
        this.isDriver = isDriver;
        this.isAdmin = isAdmin;
    }
}