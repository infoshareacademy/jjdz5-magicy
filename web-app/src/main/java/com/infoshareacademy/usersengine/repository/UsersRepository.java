package com.infoshareacademy.usersengine.repository;

import com.infoshareacademy.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class UsersRepository {
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        users.add(new User("admin", "p"));
        users.add(new User("root", "r"));
    }


    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return users.stream().filter(u -> u.getName().equals(username) && u.getPassword().equals(password)).findAny();
    }

    public List<User> findAllUsers() {
        return users;
    }
}