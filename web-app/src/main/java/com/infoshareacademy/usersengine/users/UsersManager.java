package com.infoshareacademy.usersengine.users;

import com.infoshareacademy.User;

import java.util.List;
import java.util.Optional;

public interface UsersManager {
    Optional<User> findUserByUsernameAndPassword(String username, String password);
    List<User> findAllUsers();
}